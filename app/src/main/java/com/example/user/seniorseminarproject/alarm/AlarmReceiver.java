package com.example.user.seniorseminarproject.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import java.util.Calendar;

/**
 * This class handles any alarm events sent from the alarm manger.
 * Events will be handled when the application is on or off on the
 * user's phone.
 */
public class AlarmReceiver extends BroadcastReceiver{
    AlarmManager alarmManager;

    //@Override
    public void onReceive(Context context, Intent intent) {
        //create/handle wakelock
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        //use alarmId to decide which action to take depending on which alarm fired.
        int id;
        id = intent.getExtras().getInt("alarmId");
        System.out.println("ID: " +id);
        switch(id){
            case 5:     //action for alarm heart rate event
                Log.i("Survey Alarm", "Survey Alarm Received..."+id);
                AlarmService a = new AlarmService();
                context.startService(new Intent(context, AlarmService.class));

                // send notification
                NotificationTask notificationTask = new NotificationTask(context);
                notificationTask.sendNotification("Please select your current zone.");
                break;
            default:
                System.out.println("Could not find alarmId: " + id);
        }

        wl.release();
    }

    public void SetAlarm(Context context) {
        Log.i("Survey Alarm", "Alarm is set.");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i1 = new Intent(context, AlarmReceiver.class);
        Intent i2 = new Intent(context, AlarmReceiver.class);
        Intent i3 = new Intent(context, AlarmReceiver.class);
        PendingIntent pi;

        //survey alarm runs every 1 mintute (set in milliseconds)
        i1.putExtra("alarmId", 5);
        pi = PendingIntent.getBroadcast(context, 5, i1, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*1 ,pi);
    }

}

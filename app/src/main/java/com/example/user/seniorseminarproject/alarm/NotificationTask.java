package com.example.user.seniorseminarproject.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.user.seniorseminarproject.R;
import com.example.user.seniorseminarproject.activities.MainActivity;
import com.example.user.seniorseminarproject.activities.ZoneSelectActivity;

/**
 * Created by user on 11/1/2016.
 */
public class NotificationTask {
    Context mContext;

    public NotificationTask(Context context){
        mContext = context;
    }

    //driver to start async notification task
    public void sendNotification(String message){
        SendNotificationTask nt = new SendNotificationTask();
        nt.execute(message);
    }

    // async task to send a notification to the android phone
    public class SendNotificationTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            //((GlobalStore) mContext.getApplicationContext()).setLastNotificationDate(new Date());
            //String date = new Gson().toJson(new Date());
            //editor.putString("lastNotificationDate",date);
            //editor.commit();
            Intent notificationIntent = new Intent(mContext, ZoneSelectActivity.class);
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(notificationIntent);

            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);

            Notification notification = builder.setContentTitle(params[0])
                    .setContentText("Please fill out a survey about your day!")
                    .setTicker("Pregnancy Diary")
                    .setVibrate(new long[] { 1000, 1000 })
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setLights(Color.RED, 3000, 3000)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent).build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;

            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);
            return null;
        }
    }

}

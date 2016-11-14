package com.example.user.seniorseminarproject.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

//created when app is installed, creates alarms, persists even when app is closed.
//BUG: Alarm fires as soon as being initiated, did not have time to fix, good for testing survey notification but needs to be fixed.

public class AlarmService extends Service {

    private AlarmReceiver alarm = new AlarmReceiver();

    //sets up alarms
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Alarm Service running...");
        return START_STICKY;        //makes service persist
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    //called when service is destroyed (mostly for debugging)
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("Alarm Service stopped...");
    }
}

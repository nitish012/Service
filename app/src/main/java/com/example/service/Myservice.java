package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class Myservice extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("tag", "onBind: ");
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("tag", "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("tag", "onStartCommand: "+Thread.currentThread().getId());

       // stopSelf();
        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("tag", "onDestroy: ");
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.i("tag", "unbindService: ");
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i("tag", "onRebind: ");
    }
}

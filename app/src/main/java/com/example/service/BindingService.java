package com.example.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import static com.example.service.Image.CHANNEL_ID;

public class BindingService extends Service {

    MediaPlayer player;
    private IBinder mbinder = new MyServiceBinder();

    public class MyServiceBinder extends Binder {
        public BindingService getservice() {
            //for getting context of MyService
            return BindingService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.song);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText("running...")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        return super.onStartCommand(intent, flags, startId);

    }
    //    public boolean isPlaying(){
//
//       return player.isPlaying();
//    }




    public void play() {
        player.start();
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }

    public void stopService() {
        stopPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private void stopPlayer() {
//        if (player != null) {
//            player.release();
//            player = null;
//            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
//        }
        if (player != null) {
            player.pause();
            player.seekTo(0);
        }
    }
}

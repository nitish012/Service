package com.example.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;

public class MusicPlayer extends AppCompatActivity {

    private Button play,pause,stop,move;
    private Intent serviceintent;
    private BindingService myService;
    private boolean isbind = false;
    public static final String  CHANNEL_ID="Example service channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        findId();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    ServiceConnection  myConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder binder) {
            Log.d("ServiceConnection", "connected");
            BindingService.MyServiceBinder myServiceBinder =(BindingService.MyServiceBinder)binder;
            myService=myServiceBinder.getservice();
            Log.i("tag","MY_SERVICE"+myService);
            isbind=true;
            // myService.work();
        }
        public void onServiceDisconnected(ComponentName name) {
            Log.d("ServiceConnection", "disconnected");

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MusicPlayer.this,BindingService.class);
        bindService(intent,myConnection, Context.BIND_AUTO_CREATE);

        Intent serviceIntent = new Intent(this, BindingService.class);
       // serviceIntent.putExtra("inputExtra", input);

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    private void findId() {
        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        stop=findViewById(R.id.stop);
        move=findViewById(R.id.move);
    }

    public void play(View view) {

            myService.play();

    }

    public void pause(View view) {
       myService.pause();
    }

    public void stop(View view) {
        myService.stopService();
        Intent serviceIntent = new Intent(this, BindingService.class);
        stopService(serviceIntent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(myConnection);

    }

    public void move(View view) {

        serviceintent=new Intent(this,PlayerMove.class);
        startActivity(serviceintent);

    }
}

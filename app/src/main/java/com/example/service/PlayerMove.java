package com.example.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlayerMove extends AppCompatActivity {

    private Button play,pause,stop;
    MediaPlayer player;
    private Intent serviceintent;
    private BindingService myService;
    private boolean isbind= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_move);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,BindingService.class);
        bindService(intent,myConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection  myConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder binder) {
            Log.d("ServiceConnection", "connected");
            BindingService.MyServiceBinder myServiceBinder =(BindingService.MyServiceBinder)binder;
            myService=myServiceBinder.getservice();
            Log.i("second","MY_SERVICE"+myService);
            isbind= true;
            // myService.work();
        }
        public void onServiceDisconnected(ComponentName name) {
            Log.d("ServiceConnection", "disconnected");

        }
    };
    public void player(View view) {

        myService.play();
    }

    public void pauser(View view) {
        if (isbind) {
            myService.pause();
        }
    }

    public void stoper(View view) {
        if (isbind)
        myService.stopService();
    }


//    @Override
//    protected void onStop() {
//        super.onStop();
//        unbindService(myConnection);
//        Log.i("stop second", "onStop: ");
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(myConnection);


    }
}

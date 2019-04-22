package com.example.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Unbound extends AppCompatActivity {

    private Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbound);
        findId();
        Log.i("unbound", "onCreate: "+Thread.currentThread().getId());

    }

    private void findId() {
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
    }

    public void startService(View view) {
        startService(new Intent(this,Myservice.class));
    }

    public void stopService(View view) {
        stopService(new Intent(this,Myservice.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("unbound", "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("unbound", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("unbound", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("unbound", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("unbound", "onDestroy: ");
    }
}

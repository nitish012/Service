package com.example.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button unboundlifecycle,download,music;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        listener();
    }

    private void listener() {

        unboundlifecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,Unbound.class);
                startActivity(intent);
            }
        });


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,Image.class);
                startActivity(intent);
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent=new Intent(MainActivity.this,MusicPlayer.class);
                startActivity(intent);
            }
        });

    }

    private void findId() {
        unboundlifecycle=findViewById(R.id.unboundlifecycle);
       download=findViewById(R.id.download);
       music=findViewById(R.id.music);

    }


}

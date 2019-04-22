package com.example.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Image extends AppCompatActivity {

    private Button single,parallel;
    ImageView im1,im2,im3;

    private Bitmap bitmap;
    public static final String CHANNEL_ID="DownloadService";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        findId();
        createNotificationChannel();
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new getBitmap(),new IntentFilter("getBitmap"));
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(DownloadService.TAG, "onClick: ");
                String url1="https://tineye.com/images/widgets/mona.jpg";
                String url2="https://tineye.com/images/widgets/mona.jpg";
                String url3="https://tineye.com/images/widgets/mona.jpg";
                String[] url={url1,url2,url3};
                Intent serviceIntent=new Intent(Image.this,DownloadService.class);
                serviceIntent.putExtra("imgview",url);
                startService(serviceIntent);

            }
        });
        parallel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url1="https://tineye.com/images/widgets/mona.jpg";
                String url2="https://tineye.com/images/widgets/mona.jpg";
                String url3="https://tineye.com/images/widgets/mona.jpg";

                String[] url={url1,url2,url3};

                Intent serviceIntent=new Intent(Image.this,ParallelService.class);
                serviceIntent.putExtra("imgview",url);
                startService(serviceIntent);


            }
        });


    }
    private void createNotificationChannel() {

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



    private void findId() {
        single=findViewById(R.id.single);
        parallel=findViewById(R.id.parallel);
        im1=findViewById(R.id.im1);
        im2=findViewById(R.id.im2);
        im3=findViewById(R.id.im3);
        //input=findViewById(R.id.input);

    }


    class getBitmap extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(DownloadService.TAG, "onReceive: ");


            if (intent.getIntExtra("first",3)==10) {
               // if (intent.getAction().equals("getBitmap")) {

                    if (intent.getIntExtra("value", 5) == 0) {
                        Bitmap bmpvalue = intent.getParcelableExtra("getBitmapRef");
                        im1.setImageBitmap(bmpvalue);


                    }

                    if (intent.getIntExtra("value", 5) == 1) {
                        Bitmap bmpvalue = intent.getParcelableExtra("getBitmapRef");
                        im2.setImageBitmap(bmpvalue);
                    }

                    if (intent.getIntExtra("value", 5) == 2) {
                        Bitmap bmpvalue = intent.getParcelableExtra("getBitmapRef");
                        im3.setImageBitmap(bmpvalue);
                    }


               // }
            }

            if (intent.getIntExtra("second",3)==20) {
                Bitmap[] bitmaps = (Bitmap[]) intent.getParcelableArrayExtra("getBitmapRef");
                Log.i("bitmap", "onReceive: " + intent.getParcelableArrayExtra("getBitmapRef"));
                im1.setImageBitmap(bitmaps[0]);
                im2.setImageBitmap(bitmaps[1]);
                im3.setImageBitmap(bitmaps[2]);

            }
        }
    }


}

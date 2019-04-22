package com.example.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ThemedSpinnerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ParallelService extends Service {

    Bitmap[] bitmapArray = new Bitmap[3];
    public static final String TAG="TAG";
    private Handler handler;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        final LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(getApplicationContext());
        Log.i(TAG, "onStartCommand: ");
        final String url[]=intent.getStringArrayExtra("imgview");


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<url.length;i++) {
                    InputStream in = null;
                    try {
                        in = new URL(url[i]).openStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "url: " + url);
                    Log.i(TAG, "InputStream: " + in);
                    Bitmap bmp = BitmapFactory.decodeStream(in);
                    Log.i(TAG, "onHandleIntent: " + bmp);
                    bitmapArray[i] = bmp;
                    Log.i(TAG, "onHandleIntent: ");
                }


                        Intent broadcastIntent = new Intent("getBitmap");
                        broadcastIntent.putExtra("getBitmapRef", bitmapArray);
                        broadcastIntent.putExtra("second",20);
                        Log.i(TAG, "run: "+bitmapArray);
                        Log.i(TAG, "broadcastIntent: " + broadcastIntent);
                        localBroadcastManager.sendBroadcast(broadcastIntent);





            }
        }).start();

        return START_NOT_STICKY;




    }
}


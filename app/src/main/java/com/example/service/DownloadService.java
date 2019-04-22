package com.example.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import static com.example.service.Image.CHANNEL_ID;

public class DownloadService extends IntentService {
    public static final String TAG="TAG";
   // String url="https://tineye.com/images/widgets/mona.jpg";
    public DownloadService() {
        super("DownloadService");
        setIntentRedelivery(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Example IntentService")
                    .setContentText("Running...")
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .build();

            startForeground(1, notification);



        }
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        final LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(getApplicationContext());
            try {

                String url[]=intent.getStringArrayExtra("imgview");
                for(int i=0;i<url.length;i++) {
                    Log.i(TAG, "onHandleIntent: ");
                    InputStream in = new java.net.URL(url[i]).openStream();
                    Log.i(TAG, "url: " + url);
                    Log.i(TAG, "InputStream: " + in);
                    Bitmap bmp = BitmapFactory.decodeStream(in);
                    Log.i(TAG, "onHandleIntent: " + bmp);
                    Intent broadcastIntent = new Intent("getBitmap");
                    broadcastIntent.putExtra("getBitmapRef", bmp);
                    broadcastIntent.putExtra("first",10);
                    broadcastIntent.putExtra("value",i);
//                    IntentFilter intentFilter = new IntentFilter();
//                    intentFilter.addAction("getBitmap");
                    Log.i(TAG, "broadcastIntent: " + broadcastIntent);
                    localBroadcastManager.sendBroadcast(broadcastIntent);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


    }


}

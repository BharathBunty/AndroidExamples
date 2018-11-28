package com.example.bkanjula.cameraapp.PushNotificationExample;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.bkanjula.cameraapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFireBaseMessagingService extends FirebaseMessagingService  {

    private static final String TAG = "MyFirebaseMsgService";
    private static int ADMIN_CHANNEL_ID = 0;
    NotificationManager notificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Notification Message TITLE: " + remoteMessage.getNotification().getTitle());
        Log.d(TAG, "Notification Message BODY: " + remoteMessage.getNotification().getBody());
        Log.d(TAG, "Notification Message DATA: " + remoteMessage.getData().toString());

        sendNotification(remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(), remoteMessage.getData());

    }

    private void sendNotification(String title, String body, Map<String,String> data) {

        PendingIntent contentIntent = null;

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupChannels(title,body);
        }

        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_camera_black_24dp))
                .setSmallIcon(R.drawable.ic_menu_camera)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent);

         notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ADMIN_CHANNEL_ID, notificationBuilder.build());

        ADMIN_CHANNEL_ID++;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(String title, String body){

        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(String.valueOf(ADMIN_CHANNEL_ID),  title, NotificationManager.IMPORTANCE_LOW);
        adminChannel.setDescription(body);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }
}

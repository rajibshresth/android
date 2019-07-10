package com.e.loginregister.channel;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class notification {

    Context context;
    public final static String Channel_1 = "Channel1";
    public final static String Channel_2 = "Channel2";
    public final static String Channel_3 = "Channel3";
    //    Constructor
    public notification(Context context) {
        this.context = context;
    }

    public void notification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    Channel_1,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is chanel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    Channel_2,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("This is chanel 2");
            NotificationChannel channel3 = new NotificationChannel(
                    Channel_3,
                    "Channel 3",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("This is chanel 3");

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);

        }


    }
}
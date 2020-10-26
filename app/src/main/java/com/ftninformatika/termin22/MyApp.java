package com.ftninformatika.termin22;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

//peti korak, kreiramo notifikaciju
public class MyApp extends Application {
    //osmi korak. Moramo ga posle napraviti piblic kako bi svi ostali mogli vidjeti.
    public static final String CHANNEL_ID = "my_channel_ID";

    // sesti korak
    @Override
    public void onCreate() {
        super.onCreate();
        //11. korak
        createNotificationChannel();

    }
    //sedmi korak
    private void createNotificationChannel(){
        //SDK INT koji je Softver Development Kit, koja je verzija Androida. Ako je stariji Android, on se nece izvrsiti.
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    //deveti korak
            CHANNEL_ID,
            "My notif",
                    NotificationManager.IMPORTANCE_DEFAULT);
            //deseti korak
            channel.setDescription("Description");
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
    }
}

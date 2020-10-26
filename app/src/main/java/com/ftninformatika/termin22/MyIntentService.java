package com.ftninformatika.termin22;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

//23. korak
public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public static final String MSG_TAG = "msg_tag";
    //brojevi nemaju nikakve veze, sami odredjujemo
    public static final int NOTIF_ID = 5;

    //moramo da napravimo deffault konstruktor
    public MyIntentService() {
        super(TAG);

    }
    //27. korak

    @Override
    public void onCreate() {
        super.onCreate();
        //27. korak. Treba da kreiramo notifikaciju, posto nas servis "trci", on jos uvek radi!
        showNotifIfNeeded();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //24. korak, ali prvo kreiramo MSG TAG
        String msg = intent.getStringExtra(MSG_TAG);
        //26. korak posto radi u pozadinskoj niti, ovde mozemo da uspavamo sistem
        for (int i = 0; i < 5; i++) {
            SystemClock.sleep(1000);
           //promenimo ga u e, kako bismo videli gresku, umesto d
            Log.e(TAG, msg + ":" + i);
            //31. korak
            updateCountDownNotification(i,msg);

        }
    }

    //25. korak - kreiramo notif kako bi bio validan i za starije aplikace.
    //Prvo moramo videti da li je nasa verzija veca od 26
    private void showNotifIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, MyApp.CHANNEL_ID)
                    .setContentTitle("Our Intent Service")
                    .setContentText("...Working hard...")
                    .setSmallIcon(R.drawable.ic_baseline_local_pizza_24)
                    .build();
            //on zasad jos nije foreground, moramo pozvati - 19. korak. Stavi me u prvi plan
            startForeground(NOTIF_ID, notification);
        }
    }

    //30. korak. Proveri da li mi je verzija androida veca, kako bi app radio
    private void updateCountDownNotification(int seconds, String string) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new NotificationCompat.Builder(this, MyApp.CHANNEL_ID)
                    .setContentTitle("Nova notifikacija")
                    .setContentText(string + " - " + seconds)
                    .setSmallIcon(R.drawable.ic_baseline_local_pizza_24)
                    .build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(NOTIF_ID, notification);

        }
    }
}

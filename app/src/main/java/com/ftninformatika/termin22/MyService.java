package com.ftninformatika.termin22;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

//12. korak
public class MyService extends Service {
    public static final String MSG_TAG = "msg_tag";

    //18. korak - treba nam notif ID
    public static final int NOTIF_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    // implementiramo jos jednu metodu - 13. korak

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //zelimo da dobijemo aktivnost, neku poruku - 14. korak, ali pre toga pravimo TAG
        String msg = intent.getStringExtra(MSG_TAG);
        //20. korak, deo 19. koraka. Mi to zelimo da prikazemo u pravom planu
        showServiceNotification(msg);
        //to ide na kraj naseg servisa
        return Service.START_NOT_STICKY;
    }
    //16.korak. Da prikazemo notifikaciju, koja sadrzi prethodni String

    private void showServiceNotification (String msg){
        //zelimo da otvorimo MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        //bice poslat, koji ce se desiti u buducnosti, mada on sadrzi intent u sebi.
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //17. korak
        Notification notification = new NotificationCompat.Builder(this,MyApp.CHANNEL_ID)
                .setContentTitle("Our Foreground Service")
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_baseline_local_pizza_24)
                //postavljamo ono sto smo prethodno kreirali, kako bi nam prikazao aktivnost.
                .setContentIntent(pendingIntent)
                .build();
        //on zasad jos nije foreground, moramo pozvati - 19. korak. Stavi me u prvi plan
        startForeground(NOTIF_ID, notification);
    }
}

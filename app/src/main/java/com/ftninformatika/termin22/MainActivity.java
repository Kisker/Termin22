package com.ftninformatika.termin22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button bStart;
    private Button bStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //treci korak
        editText = findViewById(R.id.etText);
        bStart = findViewById(R.id.bStart);
        bStop = findViewById(R.id.bStop);

        //cetvrti korak, pozivamo interface
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMyService();
            }
        });
        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // stopMyService();
                //29. korak umesto stopMyService, pozivamo
                startMyIntentService();
            }
        });
    }
     // prvo cemo napraviti dve metode

    private void startMyService(){
        //21. korak. Izvlacimo nasu poruku. Prvo u manifestu oderdimo nas servis, pre nego sto krenemo sa ovim korakom!
        String msg = editText.getText().toString();
        //pravimo intent, kako bismo pokrenuli nas servis
        Intent intent = new Intent(this, MyService.class);
        //zato imamo tagove, kako bi nam pozvao MyService
        intent.putExtra(MyService.MSG_TAG, msg);
        //startForegroundService nam proveri da li je Build veci od 26, on ce da pozove startForegroundService, ako nije poziva obican servis. Veoma bitno.
        ContextCompat.startForegroundService(this, intent);

    }

    private void stopMyService (){
        // 22. korak. Posle ovoga idemo na manifest opet, kako bismo stavili novi zahtev
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);


    }
    // 28. korak - dodajemo jos dve metode
    private void startMyIntentService (){
        String msg = editText.getText().toString();
        //pravimo intent, kako bismo pokrenuli nas servis
        Intent intent = new Intent(this, MyIntentService.class);
        //zato imamo tagove, kako bi nam pozvao MyService
        intent.putExtra(MyIntentService.MSG_TAG, msg);
        //startForegroundService nam proveri da li je Build veci od 26, on ce da pozove startForegroundService, ako nije poziva obican servis. Veoma bitno.
        ContextCompat.startForegroundService(this, intent);


    }
}
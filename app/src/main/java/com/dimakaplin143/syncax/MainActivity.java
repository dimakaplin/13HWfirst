package com.dimakaplin143.syncax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private GregorianCalendar calendar;
    private Button sync;
    private Intent syncIntent;
    private int hour;

    private final String MORNING = "http://morning";
    private final String AFTERNOON = "http://afternoon ";
    private final String EVENING = "http://evening";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        calendar = new GregorianCalendar();
        sync = findViewById(R.id.sync);
        syncIntent = new Intent(Intent.ACTION_SYNC);
        sync.setOnClickListener(v -> {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            if( hour >= 6 && hour <= 14) {
                Log.d("Intent", "утро " + hour + " " + Uri.parse(MORNING));
                syncIntent.setData(Uri.parse(MORNING));
            } else if (hour > 14 && hour <= 15) {
                syncIntent.setData(Uri.parse(AFTERNOON));
                Log.d("Intent", "обед " + hour + " " + Uri.parse(AFTERNOON));
            } else {
                syncIntent.setData(Uri.parse(EVENING));
                Log.d("Intent", "вечер " + hour + " " + Uri.parse(EVENING));
            }
            syncIntent.addCategory(Intent.CATEGORY_DEFAULT);
            syncIntent.addCategory(Intent.CATEGORY_BROWSABLE);
            syncIntent.putExtra(Intent.EXTRA_TEXT, calendar.getTime().toString());

            if (syncIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(syncIntent);
            } else {
                Log.d("Intent", "Не получается обработать намерение!");
            }
        });

    }
}

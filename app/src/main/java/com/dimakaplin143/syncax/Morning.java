package com.dimakaplin143.syncax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Morning extends AppCompatActivity {

    private TextView timeView;
    private Button toSyncBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning);
        initViews();
        Log.d("Intent", "утро");
    }

    public void initViews() {
        timeView = findViewById(R.id.time);
        Intent parentIntent = getIntent();

        String time = parentIntent.getStringExtra(Intent.EXTRA_TEXT);
        timeView.setText("asd");

        toSyncBtn = findViewById(R.id.to_sync);
        toSyncBtn.setOnClickListener(v-> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}

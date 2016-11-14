package com.example.user.seniorseminarproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.seniorseminarproject.R;

public class MainActivity extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);

        // Create intent for sending username
        Intent i = getIntent();
        username = (String) i.getSerializableExtra("username");
    }

    public void onSelectZoneClick(View view) {
        Intent intent = new Intent(this, ZoneSelectActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void onZoneInformationClick(View view) {
        Intent intent = new Intent(this, ZoneInformationActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


}

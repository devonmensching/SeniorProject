package com.example.user.seniorseminarproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.seniorseminarproject.R;
import com.example.user.seniorseminarproject.database.DBOperations;
import com.example.user.seniorseminarproject.database.ZoneData;

import java.util.Calendar;

public class ZoneSelectActivity extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_select);
        Intent i = getIntent();
        username = (String) i.getSerializableExtra("username");
    }

    // Starts MainActivity when blueZoneButton is clicked
    public void onBlueZoneClick(View view) {
        // Get current time
        Calendar calendar = Calendar.getInstance();
        String time = calendar.getTime().toString();

        // Create ZoneData object for Zone selected
        ZoneData zoneData = new ZoneData(time, "Blue");

        // Put ZoneData object into Database
        DBOperations dbOperations = new DBOperations(this);
        dbOperations.addZoneData(zoneData);

        // Go to Main Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Starts MainActivity when greenZoneButton is clicked
    public void onGreenZoneClick(View view) {
        // Get current time
        Calendar calendar = Calendar.getInstance();
        String time = calendar.getTime().toString();

        // Create ZoneData object for Zone selected
        ZoneData zoneData = new ZoneData(time, "Green");

        // Put ZoneData object into Database
        DBOperations dbOperations = new DBOperations(this);
        dbOperations.addZoneData(zoneData);

        // Go to Main Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Starts MainActivity when yellowZoneButton is clicked
    public void onYellowZoneClick(View view) {
        // Get current time
        Calendar calendar = Calendar.getInstance();
        String time = calendar.getTime().toString();

        // Create ZoneData object for Zone selected
        ZoneData zoneData = new ZoneData(time, "Yellow");

        // Put ZoneData object into Database
        DBOperations dbOperations = new DBOperations(this);
        dbOperations.addZoneData(zoneData);

        // Go to Main Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // Starts MainActivity when redZoneButton is clicked
    public void onRedZoneClick(View view) {
        // Get current time
        Calendar calendar = Calendar.getInstance();
        String time = calendar.getTime().toString();

        // Create ZoneData object for Zone selected
        ZoneData zoneData = new ZoneData(time, "Red");

        // Put ZoneData object into Database
        DBOperations dbOperations = new DBOperations(this);
        dbOperations.addZoneData(zoneData);

        // Go to Main Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

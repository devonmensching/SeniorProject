package com.example.user.seniorseminarproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.seniorseminarproject.R;

public class ZoneInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_information);
    }

    public void onZoneInfoBackButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onBlueZoneInfoClick(View view){
        Intent intent = new Intent(this, BlueZoneActivity.class);
        startActivity(intent);
    }

    public void onGreenZoneInfoClick(View view){
        Intent intent = new Intent(this, GreenZoneActivity.class);
        startActivity(intent);
    }

    public void onYellowZoneInfoClick(View view){
        Intent intent = new Intent(this, YellowZoneActivity.class);
        startActivity(intent);
    }

    public void onRedZoneInfoClick(View view){
        Intent intent = new Intent(this, RedZoneActivity.class);
        startActivity(intent);
    }
}

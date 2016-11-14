package com.example.user.seniorseminarproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.user.seniorseminarproject.R;

public class BlueZoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_zone);
    }

    public void onBlueZoneBackButtonClick(View view) {
        Intent intent = new Intent(this, ZoneInformationActivity.class);
        startActivity(intent);
    }
}

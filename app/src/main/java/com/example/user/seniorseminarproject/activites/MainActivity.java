package com.example.user.seniorseminarproject.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.user.seniorseminarproject.QueryOutputActivity;
import com.example.user.seniorseminarproject.R;
import com.example.user.seniorseminarproject.database.DBOperations;
import com.example.user.seniorseminarproject.database.User;
import com.example.user.seniorseminarproject.database.ZoneData;

public class MainActivity extends AppCompatActivity {

    private EditText time;
    private EditText zone;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set GUI Components
        time = (EditText)findViewById(R.id.timeEditText);
        zone = (EditText)findViewById(R.id.zoneEditText);
        username = (EditText)findViewById(R.id.searchUserEditText);
    }

    public void queryButtonOnClick(View view){
        // Get time and zone
        String timeString = time.getText().toString();
        String zoneString = zone.getText().toString();

        // Add to database
        DBOperations db = new DBOperations(this);
        ZoneData zoneData = new ZoneData(timeString, zoneString);
        db.addZoneData(zoneData);
        String insertResult = "Row Inserted: " + timeString + ", " + zoneString;
        Log.i("ZoneData Table", insertResult);

        // Get username
        String userString = username.getText().toString();

        // Search user table
        User user = db.findUser(userString);
        String searchResult = "";
        if(user!=null) {
            searchResult = "Row Found: " + user.getUsername() + ", " + user.getPassword() + ", " +
                    user.getName() +", "+ user.getDaystart() + ", " + user.getDayend();
            Log.i("User Table", searchResult);
        }

        // Go to Question 3 page
        Intent i = new Intent(this, QueryOutputActivity.class);
        i.putExtra("insertResult", insertResult);
        i.putExtra("searchResult", searchResult);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i);
    }
}

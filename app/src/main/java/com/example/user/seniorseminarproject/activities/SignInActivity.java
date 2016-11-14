package com.example.user.seniorseminarproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.user.seniorseminarproject.R;
import com.example.user.seniorseminarproject.alarm.AlarmReceiver;
import com.example.user.seniorseminarproject.alarm.NotificationTask;
import com.example.user.seniorseminarproject.database.DBOperations;
import com.example.user.seniorseminarproject.database.User;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Sets up sync adapter
        //SyncUtils.CreateSyncAccount(this);

        // set up alarms
        AlarmReceiver alarmReceiver = new AlarmReceiver();
        alarmReceiver.SetAlarm(this);
    }

    // Starts the MainActivity when SignInButton is clicked
    public void onSignInClick(View view) {
        // Get user from EditText
        EditText username = (EditText) findViewById(R.id.usernameEditText);

        // Find inputed user in databse
        DBOperations dbOperations = new DBOperations(this);
        User user = dbOperations.findUser(username.getText().toString());

        // If present in database then go to MainActivity
        // If not send error notificiation
        if(user != null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("username", username.getText().toString());
            startActivity(intent);
        }
        else{
            NotificationTask task = new NotificationTask(this);
            task.sendNotification("Incorrect Username or Password");
        }
    }

}

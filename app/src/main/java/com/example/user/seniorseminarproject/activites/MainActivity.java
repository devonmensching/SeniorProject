package com.example.user.seniorseminarproject.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.user.seniorseminarproject.R;
import com.example.user.seniorseminarproject.database.DBOperations;
import com.example.user.seniorseminarproject.database.User;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText name;
    private EditText dayStart;
    private EditText dayEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.editText1);
        password = (EditText)findViewById(R.id.editText2);
        name = (EditText)findViewById(R.id.editText3);
        dayStart = (EditText)findViewById(R.id.editText4);
        dayEnd = (EditText)findViewById(R.id.editText5);

    }

    public void buttonOnClick(View view){
        DBOperations db = new DBOperations(this);
        User user = new User(username.getText().toString(), password.getText().toString(), name.getText().toString(),
                                dayStart.getText().toString(), dayEnd.getText().toString());
        db.addUser(user);
        Log.i("Button", "user added");
    }
}

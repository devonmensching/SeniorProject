package com.example.user.seniorseminarproject.net;

import android.content.Context;

import com.example.user.seniorseminarproject.database.DBOperations;
import com.example.user.seniorseminarproject.database.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * This is where all of the Settings data sent from the server
 * is inserted into the local SQLite database.
 */

public class ServerSync {
    private Context context;

    public ServerSync(Context context) {
        this.context = context;
    }

    public void insertRows(String tableName, JSONArray jArray) throws ParseException {
        switch (tableName) {
            case "Participants":
                insertZoneDataRows(jArray);
                break;
        }
    }

    public void insertZoneDataRows(JSONArray jArray){
        for(int i = 0; i < jArray.length(); i++){
            try {
                JSONObject row = jArray.optJSONObject(i);
                User user = new User();
                user.setUsername(row.getString("Username"));
                user.setPassword(row.getString("Password"));
                user.setName(row.getString("Name"));
                user.setDaystart(row.getString("DayStart"));
                user.setDayend(row.getString("DayEnd"));

                DBOperations db = new DBOperations(context);
                if(db.findUser(user.getUsername()) == null){
                    db.addUser(user);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
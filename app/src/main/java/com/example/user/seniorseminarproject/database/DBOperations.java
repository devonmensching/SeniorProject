package com.example.user.seniorseminarproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by user on 9/15/2016.
 */
public class DBOperations {

    private DBHandler dbHandler;
    private Context context;
    private SharedPreferences sharedPreferences;

    public DBOperations(Context context) {
        this.context = context;
        dbHandler = new DBHandler(context);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    // Add a row to the User Table
    public void addUser(User user) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        // prepare teh values for the new entry
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_USERNAME, user.getUsername());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_NAME, user.getName());
        values.put(User.COLUMN_DAYSTART, user.getDaystart());
        values.put(User.COLUMN_DAYEND, user.getDayend());

        // inter into the DB
        db.insert(User.tablename, null, values);
        Log.i("SQLite DB", "Row added to User table");

        // close the DB
        db.close();
    }

    // Find a User in the User table
    public User findUser(String username) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        // construct the SQL string
        String sqlQuery = "SELECT * FROM " + User.tablename +
                " WHERE " + User.COLUMN_USERNAME + " = " +
                username;


        // Execute the query on the DB and store the returned rows
        Cursor myCursor = db.rawQuery(sqlQuery, null);

        // Create and empty User object
        User user = new User();

        //  if at least one row is returned, point to the first entry
        // and extract the data
        if(myCursor.moveToFirst()) {
            // have to know index and type of each row in table
            user.setUsername(myCursor.getString(0));
            user.setPassword(myCursor.getString(1));
            user.setName(myCursor.getString(2));
            user.setDaystart(myCursor.getString(3));
            user.setDayend(myCursor.getString(4));
        }
        else {
            // nothing was returned
            user = null;
        }

        myCursor.close();
        db.close();
        Log.i("SQLite DB", "Row searched to User table");

        return user;
    }

    // Add a row to the ZoneData Table
    public void addZoneData(ZoneData zoneData) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        // prepare teh values for the new entry
        ContentValues values = new ContentValues();
        values.put(ZoneData.COLUMN_TIME, zoneData.getTime());
        values.put(ZoneData.COLUMN_ZONE, zoneData.getZone());

        // inter into the DB
        db.insert(ZoneData.tablename, null, values);
        Log.i("SQLite DB","Row added to ZoneData table");

        // close the DB
        db.close();
    }



}


package com.example.user.seniorseminarproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 9/15/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "SSProject.db";

    // Declare User table
    public static final String TABLE_USER = User.tablename;
    public static final String COL_USERNAME = User.COLUMN_USERNAME;
    public static final String COL_PASSWORD = User.COLUMN_PASSWORD;
    public static final String COL_NAME = User.COLUMN_NAME;
    public static final String COL_DAYSTART = User.COLUMN_DAYSTART;
    public static final String COL_DAYEND = User.COLUMN_DAYEND;

    // Declare ZoneData table
    public static final String TABLE_ZONEDATA = ZoneData.tablename;
    public static final String COL_TIME = ZoneData.COLUMN_TIME;
    public static final String COL_ZONE = ZoneData.COLUMN_ZONE;

    public DBHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL query to create User table
        String CREATE_USER_TABLE = "CREATE TABLE " +
                TABLE_USER + "(" +
                COL_USERNAME + " Text, " +
                COL_PASSWORD + " Text, " +
                COL_NAME + " Text, " +
                COL_DAYSTART + " INTEGER, " +
                COL_DAYEND + " Text)";

        // SQL query to create ZoneData table
        String CREATE_ZONEDATA_TABLE = "CREATE TABLE " +
                TABLE_ZONEDATA + "(" +
                COL_TIME + " Text, " +
                COL_ZONE + " Text)";

        // Execute the SQL commands, No return value
        db.execSQL(CREATE_USER_TABLE);
        Log.i("SQLite DB", "User table created");
        db.execSQL(CREATE_ZONEDATA_TABLE);
        Log.i("SQLite DB", "ZoneData table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        Log.i("SQLite DB", "User table dropped");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZONEDATA);
        Log.i("SQLite DB","ZoneData table dropped");

        // recreate the table by calling onCreate
        onCreate(db);
    }
}

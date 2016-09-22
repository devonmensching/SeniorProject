package com.example.user.seniorseminarproject.database;

/**
 * Created by user on 9/15/2016.
 */
public class ZoneData {

    public static final String tablename = "ZoneData";
    public static final String COLUMN_TIME = "Time";
    public static final String COLUMN_ZONE = "Zone";

    private String time;
    private String zone;

    public ZoneData(String time, String zone){
        this.time = time;
        this.zone = zone;
    }

    public String getTime(){ return time; }

    public void setTime(String time){ this.time = time; }

    public String getZone(){ return zone; }

    public void setZone(){ this.zone = zone; }
}

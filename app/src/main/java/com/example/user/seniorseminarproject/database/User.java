package com.example.user.seniorseminarproject.database;

/**
 * Created by user on 9/15/2016.
 */
public class User {

    public static final String tablename = "User";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DAYSTART = "DayStart";
    public static final String COLUMN_DAYEND = "DayEnd";

    private String username;
    private String password;
    private String name;
    private String daystart;
    private String dayend;

    public User(){}

    public User(String username, String password, String name, String daystart, String dayend) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.daystart = daystart;
        this.dayend = dayend;
    }

    public String getUsername(){ return username; }

    public void setUsername(String username){ this.username = username; }

    public String getPassword(){ return password; }

    public void setPassword(String password){ this.password = password; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getDaystart(){ return daystart; }

    public void setDaystart(String daystart){ this.daystart = daystart; }

    public String getDayend(){ return dayend; }

    public void setDayend(String dayend){ this.dayend = dayend; }

}

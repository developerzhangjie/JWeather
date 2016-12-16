package com.example.devel.jweather.db;

/**
 * Description:一些常量
 * Created by devel on 12/14/2016.
 */

public interface Constant {
    String DATABASE_NAME = "Weather.db";
    int DATABASE_VERSION = 1;
    String TABLE_NAME = "WeatherInTime";
    String ID = "_id";
    String CITY = "city";
    String WEATHER = "weather";
    String TEMPERATURE = "temperature";
    String HIGH_TEMPERATURE = "high_temperature";
    String LOW_TEMPERATURE = "low_temperature";
    String CREATURE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " integer primary key autoincrement," +
            CITY + " text," +
            WEATHER + " text," +
            TEMPERATURE + " text," +
            HIGH_TEMPERATURE + " text," +
            LOW_TEMPERATURE + " text" +")";
}

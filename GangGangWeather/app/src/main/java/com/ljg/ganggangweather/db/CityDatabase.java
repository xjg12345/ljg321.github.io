package com.ljg.ganggangweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//SQLiteOpenHelper帮助类，借助这个类就可以非常简单地对数据库进行创建和升级
public class CityDatabase extends SQLiteOpenHelper {
    public static final String TABLE_NAME="citys";
    public static final String ID="id";
    public static final String CURRENT_NAME="currentName";
    public static final String PROVINCE_NAME="province";
    public static final String CITY_NAME="city";
    public static final String COUNTRY_NAME="country";
    public static final String MIN_TEMP="minTemp"; //最低气温
    public static final String MAX_TEMP="maxTemp"; //最高气温
    public static final String WEATHER_DESC="weatherDesc"; //天气描述
    public static final String WEATHER_CODE="weatherCode";
    public static final String LAST_UPDATE="lastUpdate";


    public CityDatabase(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    public void createTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+TABLE_NAME+"(" +
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                CURRENT_NAME+" TEXT NOT NULL," +
                PROVINCE_NAME+" TEXT NOT NULL," +
                CITY_NAME+" TEXT NOT NULL," +
                COUNTRY_NAME+" TEXT," +
                MIN_TEMP+" TEXT NOT NULL," +
                MAX_TEMP+" TEXT NOT NULL," +
                WEATHER_DESC+" TEXT NOT NULL," +
                WEATHER_CODE+" TEXT NOT NULL," +
                LAST_UPDATE+" Integer NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

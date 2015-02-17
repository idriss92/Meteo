package com.partiels.meteo.algo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MeteoData {

    private final String name;
    private String temperature;
    private String weather;
    private String humidity;
    private String pressure;
    private String speed;
    private String direction;
    private Date date;

    private SQLiteDatabase database;
    private DataBase myDataBase;
    private String[] allColumns = {
    		DataBase.TABLE_METEO_COLUMN_NAME,
    		DataBase.TABLE_METEO_COLUMN_TEMP,
    		DataBase.TABLE_METEO_COLUMN_WEATH,
    		DataBase.TABLE_METEO_COLUMN_HUM,
    		DataBase.TABLE_METEO_COLUMN_SPE,
    		DataBase.TABLE_METEO_COLUMN_DATE
    };

    public MeteoData(String name, Context context) {
        this.name = name;
        this.myDataBase = new DataBase(context);
    }

    public String getName () {
        return this.name;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }


    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

  

    public String getDate() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private void open () {
        this.database = this.myDataBase.getWritableDatabase();
    }

    private void close () {
        this.myDataBase.close();
    }

    public void save () {
        this.open();
        this.date = new Date();
        ContentValues values = new ContentValues();
        values.put(DataBase.TABLE_METEO_COLUMN_NAME, this.name);
        values.put(DataBase.TABLE_METEO_COLUMN_TEMP, this.temperature);
        values.put(DataBase.TABLE_METEO_COLUMN_WEATH, this.weather);
        values.put(DataBase.TABLE_METEO_COLUMN_HUM, this.humidity);
        values.put(DataBase.TABLE_METEO_COLUMN_SPE, this.speed);
        values.put(DataBase.TABLE_METEO_COLUMN_DATE, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(this.date));
        this.database.insert(DataBase.TABLE_METEO, null, values);
        this.close();
    }

    public void delete () {
        this.open();
        this.database.delete(DataBase.TABLE_METEO, DataBase.TABLE_METEO_COLUMN_NAME + " = '" + this.name +"'", null);
        this.close();
    }

    public void load () {
        if (exist()) {
            this.open();
            Cursor cursor = database.query(DataBase.TABLE_METEO, allColumns,
            		DataBase.TABLE_METEO_COLUMN_NAME + " = '" + this.name +"'", null, null, null, null);
            cursor.moveToFirst();
            this.temperature = cursor.getString(cursor.getColumnIndex(DataBase.TABLE_METEO_COLUMN_TEMP));
            this.weather = cursor.getString(cursor.getColumnIndex(DataBase.TABLE_METEO_COLUMN_WEATH));
            this.humidity = cursor.getString(cursor.getColumnIndex(DataBase.TABLE_METEO_COLUMN_HUM));
            this.speed = cursor.getString(cursor.getColumnIndex(DataBase.TABLE_METEO_COLUMN_SPE));
            String dateString = cursor.getString(cursor.getColumnIndex(DataBase.TABLE_METEO_COLUMN_DATE));
            this.date = null;
            try {
                this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(dateString);
            } catch (ParseException ex) {
                Logger.getLogger(MeteoData.class.getName()).log(Level.SEVERE, null, ex);
            }
            cursor.close();
            this.close();
        }
    }

    public boolean exist () {
        this.open();
        Cursor cursor = database.query(DataBase.TABLE_METEO, allColumns,
        		DataBase.TABLE_METEO_COLUMN_NAME + " = '" + this.name +"'", null, null, null, null);
        boolean exist = cursor.getCount() == 1;
        cursor.close();
        this.close();
        return exist;
    }

    public boolean isValid (int minutes) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date datePlus1h = calendar.getTime();
        return datePlus1h.after(now);
    }

}

package com.partiels.meteo.algo;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {
	
	public static final String TABLE_METEO ="meteo";
	public static final String TABLE_METEO_COLUMN_NAME ="name";
	public static final String TABLE_METEO_COLUMN_TEMP ="temperature";
	public static final String TABLE_METEO_COLUMN_WEATH ="weather";
	public static final String TABLE_METEO_COLUMN_HUM ="humidity";
	public static final String TABLE_METEO_COLUMN_SPE ="speed";
	public static final String TABLE_METEO_COLUMN_DATE ="date";
	
	
	
	public static final String DATABASE_NAME = "meteo.db";
	public static final int DATABASE_VERSION = 1;
	
	public DataBase(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	     String tableMeteo = "CREATE TABLE "+ TABLE_METEO +" (" +
	                TABLE_METEO_COLUMN_NAME + " TEXT PRIMARY KEY," +
	                TABLE_METEO_COLUMN_TEMP + " TEXT  NULL," +
	                TABLE_METEO_COLUMN_WEATH + " TEXT  NULL," +
	                TABLE_METEO_COLUMN_HUM + " TEXT  NULL," +
	        
	                TABLE_METEO_COLUMN_SPE + " TEXT  NULL," +
	                TABLE_METEO_COLUMN_DATE + " TEXT NOT NULL" +
	        ");";
	        db.execSQL(tableMeteo);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_tables = "DROP TABLE IF EXISTS "+ TABLE_METEO +";";
        db.execSQL(drop_tables);
        onCreate(db);
	}

}

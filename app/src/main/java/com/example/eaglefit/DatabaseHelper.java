package com.example.eaglefit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EagleFitData";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORKOUTS = "Workouts";
    private static final String TABLE_USER_PROGRESS = "UserProgress";
    private static final String TABLE_USER_INFO = "UserInformation";

    private static final String[] tables = {

    };

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String workouts = "CREATE TABLE " + TABLE_WORKOUTS + "(EXERCISE_NAME TEXT, CHEST INTEGER, BACK INTEGER, SHOULDERS INTEGER, ARMS INTEGER, LEGS INTEGER)";
        String userProgress = "CREATE TABLE " + TABLE_USER_PROGRESS + "(WORKOUT_COUNTER INTEGER PRIMARY KEY AUTOINCREMENT, DATE INTEGER, BENCH_PRESS REAL, SQUATS REAL, DEADLIFTS REAL)"; //ADD ALL EXERCISES HERE
        String userInfo = "CREATE TABLE " + TABLE_USER_INFO + "(NAME TEXT, BIRTHDAY INTEGER, HEIGHT REAL, WEIGHT REAL)";

        db.execSQL(workouts);
        db.execSQL(userProgress);
        db.execSQL(userInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_WORKOUTS);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_USER_PROGRESS);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_USER_INFO);
    }

    public boolean addData(String tableName, ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //if(table.equals(TABLE))
        //contentValues.put();
    }
}

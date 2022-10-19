package com.example.eaglefit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

    private static final String DATABASE_NAME = "EagleFitData";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_WORKOUTS = "Workouts";
    private static final String TABLE_USER_PROGRESS = "UserProgress";
    private static final String TABLE_USER_INFO = "UserInformation";

    private static final String[][] COLUMNS_WORKOUTS = {
            {"EXERCISE_NAME", "TEXT"},
            {"CHEST", "INTEGER"},
            {"BACK", "INTEGER"},
            {"SHOULDERS", "INTEGER"},
            {"ARMS", "INTEGER"},
            {"LEGS", "INTEGER"}
    };

    private static final String[][] COLUMNS_USER_PROGRESS = {
            {"WORKOUT_COUNTER", "INTEGER PRIMARY KEY AUTOINCREMENT"},
            {"DATE", "INTEGER"},
            {"WORKOUT_DATA", "TEXT"}
    };

    private static final String[][] COLUMNS_USER_INFORMATION = {
            {"NAME", "TEXT"},
            {"BIRTHDAY", "INTEGER"},
            {"HEIGHT", "REAL"},
            {"WEIGHT", "REAL"}
    };


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableBuilder workoutTable = new TableBuilder(TABLE_WORKOUTS, COLUMNS_WORKOUTS);
        TableBuilder userProgressTable = new TableBuilder(TABLE_USER_PROGRESS, COLUMNS_USER_PROGRESS);
        TableBuilder userInformationTable = new TableBuilder(TABLE_USER_INFO, COLUMNS_USER_INFORMATION);

        db.execSQL(workoutTable.getDeclarationString());
        db.execSQL(userProgressTable.getDeclarationString());
        db.execSQL(userInformationTable.getDeclarationString());

        //Generated Commands:
        //CREATE TABLE Workouts (EXERCISE_NAME TEXT, CHEST INTEGER, BACK INTEGER, SHOULDERS INTEGER, ARMS INTEGER, LEGS INTEGER
        //CREATE TABLE UserProgress (WORKOUT_COUNTER INTEGER PRIMARY KEY AUTOINCREMENT, DATE INTEGER, BENCH_PRESS REAL, SQUATS REAL, DEADLIFTS REAL)
        //CREATE TABLE UserInformation (NAME TEXT, BIRTHDAY INTEGER, HEIGHT REAL, WEIGHT REAL)
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO);
    }

    public boolean addItemToDatabase(String tableName, String columnName, String item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName, item);

        long result = db.insert(tableName, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public boolean addItemToDatabase(String tableName, String columnName, int item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName, item);

        long result = db.insert(tableName, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public boolean addItemToDatabase(String tableName, String columnName, float item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName, item);

        long result = db.insert(tableName, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public Cursor getTableFromDatabase(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getRowFromDatabase(String tableName, String rowName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + rowName + " FROM " + tableName;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}

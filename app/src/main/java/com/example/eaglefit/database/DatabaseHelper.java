package com.example.eaglefit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static DatabaseHelper instance;

    public static final String DATABASE_NAME = "EagleFitData";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_WORKOUTS = "Workouts";
    public static final String TABLE_USER_PROGRESS = "UserProgress";
    public static final String TABLE_USER_INFO = "UserInformation";
    public static final String TABLE_PLANS = "Plans";
    public static final String TABLE_USER_WORKOUTS = "UserWorkouts";
    public static final String TABLE_EXERCISES_BACKLOG = "SelectedExercisesBacklog";

    public static final String[][] COLUMNS_WORKOUTS = {
            {"Exercise_Name", "TEXT"},
            {"Exercise_Description", "TEXT"},
            {"MUSCLES_WORKED_1", "TEXT"},
            {"MUSCLES_WORKED_2", "TEXT"},
            {"MUSCLES_WORKED_3", "TEXT"}
    };

    public static final String[][] COLUMNS_USER_PROGRESS = {
            {"WORKOUT_COUNTER", "INTEGER PRIMARY KEY AUTOINCREMENT"},
            {"DATE", "INTEGER"},
            {"WORKOUT_DATA", "TEXT"}
    };

    public static final String[][] COLUMNS_USER_INFORMATION = {
            {"KEY", "TEXT"},
            {"VALUE", "TEXT"},
    };

    public static final String[][] COLUMNS_PLANS = {
            {"WORKOUT_PLAN_NAME", "TEXT"},
            {"SUNDAY", "BIT"},
            {"MONDAY", "BIT"},
            {"TUESDAY", "BIT"},
            {"WEDNESDAY", "BIT"},
            {"THURSDAY", "BIT"},
            {"FRIDAY", "BIT"},
            {"SATURDAY", "BIT"},

            {"ACTIVE_WORKOUT", "BIT"}
    };

    public static final String[][] COLUMNS_USER_WORKOUTS = {
            {"WORKOUT_NAME", "TEXT"},
            {"Exercise_Name", "TEXT"},
            {"SETS", "INTEGER"},
            {"REPS", "INTEGER"}
    };

    public static final String[][] COLUMNS_EXERCISES_BACKLOG = {
            {"WORKOUT_PLAN_NAME", "TEXT"},
            {"Exercise_Name", "TEXT"}
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
        TableBuilder plansTable = new TableBuilder(TABLE_PLANS, COLUMNS_PLANS);
        TableBuilder userWorkoutsTable = new TableBuilder(TABLE_USER_WORKOUTS, COLUMNS_USER_WORKOUTS);
        TableBuilder exercisesBacklogTable = new TableBuilder(TABLE_EXERCISES_BACKLOG, COLUMNS_EXERCISES_BACKLOG);

        db.execSQL(workoutTable.getDeclarationString());
        db.execSQL(userProgressTable.getDeclarationString());
        db.execSQL(userInformationTable.getDeclarationString());
        db.execSQL(plansTable.getDeclarationString());
        db.execSQL(userWorkoutsTable.getDeclarationString());
        db.execSQL(exercisesBacklogTable.getDeclarationString());

        //Generated Commands:
        //CREATE TABLE Workouts (EXERCISE_NAME TEXT, CHEST INTEGER, BACK INTEGER, SHOULDERS INTEGER, ARMS INTEGER, LEGS INTEGER
        //CREATE TABLE UserProgress (WORKOUT_COUNTER INTEGER PRIMARY KEY AUTOINCREMENT, DATE INTEGER, BENCH_PRESS REAL, SQUATS REAL, DEADLIFTS REAL)
        //CREATE TABLE UserInformation (NAME TEXT, BIRTHDAY INTEGER, HEIGHT REAL, WEIGHT REAL)
        //CREATE TABLE Plans ...
        //CREATE TABLE UserWorkouts ...
        //CREATE TABLE SelectedExercisesBacklog
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PROGRESS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_WORKOUTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES_BACKLOG);
    }

    public void createNewTable(String tableName, String[][] columns) {
        SQLiteDatabase db = getWritableDatabase();
        TableBuilder tableBuilder = new TableBuilder(tableName, columns);
        db.execSQL(tableBuilder.getDeclarationString());
    }

    //DELETE EVERYTHING AFTER THIS

    public boolean addItem(String tableName, String columnName, String item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName, item);

        long result = db.insert(tableName, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public boolean addItem(String tableName, String columnName, int item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName, item);

        long result = db.insert(tableName, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public boolean addItem(String tableName, String columnName, float item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnName, item);

        long result = db.insert(tableName, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public Cursor grabTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName;
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query);
        return data;
    }

    public Cursor grabColumn(String tableName, String colName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + colName + " FROM " + tableName;
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query);
        return data;
    }

    public Cursor grabColumns(String tableName, String[] colNames)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ";
        for(String col : colNames) {
            query += col + ",";
        }
        query = query.substring(0, query.length() - 2) + "FROM " + tableName;
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query);
        return data;
    }

    public Cursor grabSpecificData(String tableName, String[] colNames, String[] columnEquals, String[] value) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ";
        if(colNames == null)
            query += "* ";
        else {
            for(String col : colNames) {
                query += col + ",";
            }
        }
        query = query.substring(0, query.length() - 2) + "FROM " + tableName + " WHERE ";
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query);
        return data;
    }
}

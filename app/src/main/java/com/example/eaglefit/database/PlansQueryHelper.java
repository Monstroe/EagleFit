package com.example.eaglefit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlansQueryHelper {

    private static final String TAG = "PlansQueryHelper";

    private DatabaseHelper databaseHelper;

    public PlansQueryHelper(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public boolean insertNewWorkoutPlan(String planName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMNS_PLANS[0][0], planName);

        long result = db.insert(DatabaseHelper.TABLE_PLANS, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public void updateWorkoutPlanName(String oldPlanName, String newPlanName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + DatabaseHelper.TABLE_PLANS + " SET " + DatabaseHelper.COLUMNS_PLANS[0][0] + "='" + newPlanName + "' WHERE " +  DatabaseHelper.COLUMNS_PLANS[0][0] + "='" + oldPlanName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public ArrayList<String> grabWorkoutPlanNames() {
        String query = "SELECT " + DatabaseHelper.COLUMNS_PLANS[0][0] + " FROM " + databaseHelper.TABLE_PLANS;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        ArrayList<String> dataList = new ArrayList<String>();
        while(data.moveToNext()) {
            dataList.add(data.getString(0));
        }

        return dataList;
    }

    public boolean doesPlanNameExist(String planName) {
        String query = "SELECT " + DatabaseHelper.COLUMNS_PLANS[0][0] + " FROM " + DatabaseHelper.TABLE_PLANS + " WHERE " + DatabaseHelper.COLUMNS_PLANS[0][0] + "='" + planName + "'";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        if(data.getCount() == 0) return false;
        return true;
    }

    public void updateWorkoutDay(String planName, int dayOfTheWeek) {
        String day = getDayOfTheWeek(dayOfTheWeek);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + DatabaseHelper.TABLE_PLANS + " SET " + day + "=1 WHERE " +  DatabaseHelper.COLUMNS_PLANS[0][0] + "='" + planName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public boolean doesWorkoutExist(String planName, int dayOfTheWeek) {
        String day = getDayOfTheWeek(dayOfTheWeek);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "SELECT " + day + " FROM " + DatabaseHelper.TABLE_PLANS + " WHERE " +  day + "=1" + " AND " + DatabaseHelper.COLUMNS_PLANS[0][0] + "='" + planName + "'";
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        if(data.getCount() == 0) return false;
        return true;
    }

    public void deleteWorkoutPlan(String planName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseHelper.TABLE_PLANS + " WHERE " + DatabaseHelper.COLUMNS_PLANS[0][0] + "='" + planName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void deleteWorkoutFromPlan(String planName, int dayOfTheWeek) {
        String day = getDayOfTheWeek(dayOfTheWeek);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + DatabaseHelper.TABLE_PLANS + " SET " + day + "=0 WHERE " + DatabaseHelper.COLUMNS_PLANS[0][0] + "='" + planName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public String getDayOfTheWeek(int dayOfTheWeek) {
        String str = null;
        switch(dayOfTheWeek) {
            case 0: str = "SUNDAY";
                break;
            case 1: str = "MONDAY";
                break;
            case 2: str = "TUESDAY";
                break;
            case 3: str = "WEDNESDAY";
                break;
            case 4: str = "THURSDAY";
                break;
            case 5: str = "FRIDAY";
                break;
            case 6: str = "SATURDAY";
                break;
        }

        return str;
    }


}

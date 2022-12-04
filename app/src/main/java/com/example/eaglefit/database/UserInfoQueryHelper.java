package com.example.eaglefit.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserInfoQueryHelper {

    private static final String TAG = "UserInfoQueryHelper";

    private DatabaseHelper databaseHelper;

    public UserInfoQueryHelper(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void insertName(String name) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + databaseHelper.TABLE_USER_INFO + " SET " + databaseHelper.COLUMNS_USER_INFORMATION[1][0] + "='" +  name + "' WHERE " + databaseHelper.COLUMNS_USER_INFORMATION[0][0] + "=" + "'NAME'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void insertBirthday(int day, int month, int year) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + databaseHelper.TABLE_USER_INFO + " SET " + databaseHelper.COLUMNS_USER_INFORMATION[1][0] + "='" +  day + month + year + "' WHERE " + databaseHelper.COLUMNS_USER_INFORMATION[0][0] + "=" + "'BIRTHDAY'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void insertAge(int age) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + databaseHelper.TABLE_USER_INFO + " SET " + databaseHelper.COLUMNS_USER_INFORMATION[1][0] + "='" +  age + "' WHERE " + databaseHelper.COLUMNS_USER_INFORMATION[0][0] + "=" + "'BIRTHDAY'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void insertHeight(int height) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + databaseHelper.TABLE_USER_INFO + " SET " + databaseHelper.COLUMNS_USER_INFORMATION[1][0] + "='" +  height + "' WHERE " + databaseHelper.COLUMNS_USER_INFORMATION[0][0] + "=" + "'HEIGHT'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void insertWeight(int weight) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + databaseHelper.TABLE_USER_INFO + " SET " + databaseHelper.COLUMNS_USER_INFORMATION[1][0] + "='" +  weight + "' WHERE " + databaseHelper.COLUMNS_USER_INFORMATION[0][0] + "=" + "'WEIGHT'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public String grabName() {
        Cursor data = grabUserInfoCursor("NAME");
        data.moveToFirst();
        String name = data.getString(0);
        Log.d(TAG, "BRUH: " + name);
        return name;
    }

    public int[] grabBirthday() {
        Cursor data = grabUserInfoCursor("BIRTHDAY");
        String birthdayStr = data.getString(0);
        int[] birthday = { Integer.parseInt(birthdayStr.substring(0, 2)), Integer.parseInt(birthdayStr.substring(2, 4)), Integer.parseInt(birthdayStr.substring(4, 8)) };
        return birthday;
    }

    public int grabAge() {
        Cursor data = grabUserInfoCursor("BIRTHDAY");
        data.moveToFirst();
        String birthdayStr = data.getString(0);
        if(birthdayStr != null) return Integer.parseInt(birthdayStr);
        return 0;
    }

    public int grabHeight() {
        Cursor data = grabUserInfoCursor("HEIGHT");
        data.moveToFirst();
        String heightStr = data.getString(0);
        if(heightStr != null) return Integer.parseInt(heightStr);
        return 0;
    }

    public int grabWeight() {
        Cursor data = grabUserInfoCursor("WEIGHT");
        data.moveToFirst();
        String weightStr = data.getString(0);
        if(weightStr != null) return Integer.parseInt(weightStr);
        return 0;
    }

    private Cursor grabUserInfoCursor(String row) {
        String query = "SELECT " + databaseHelper.COLUMNS_USER_INFORMATION[1][0] + " FROM " + databaseHelper.TABLE_USER_INFO +" WHERE " + databaseHelper.COLUMNS_USER_INFORMATION[0][0] + "='" + row + "'";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
        return data;
    }

}

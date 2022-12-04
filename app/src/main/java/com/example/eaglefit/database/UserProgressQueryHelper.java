package com.example.eaglefit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

public class UserProgressQueryHelper {

    private static final String TAG = "UserProgressQueryHelper";

    private DatabaseHelper databaseHelper;

    public UserProgressQueryHelper(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public boolean insertWorkoutData(String exerciseName, int weight, int reps) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMNS_USER_PROGRESS[1][0], exerciseName);
        contentValues.put(DatabaseHelper.COLUMNS_USER_PROGRESS[2][0], weight);
        contentValues.put(DatabaseHelper.COLUMNS_USER_PROGRESS[3][0], reps);

        long result = db.insert(DatabaseHelper.TABLE_USER_PROGRESS, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public ArrayList<Integer> grabWeightExerciseData(String exerciseName) {
        String query = "SELECT " + DatabaseHelper.COLUMNS_USER_PROGRESS[2][0] + " FROM " + databaseHelper.TABLE_USER_PROGRESS + " WHERE " + DatabaseHelper.COLUMNS_USER_PROGRESS[1][0] + "='" + exerciseName + "'";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        ArrayList<Integer> dataList = new ArrayList<Integer>();
        while(data.moveToNext()) {
            dataList.add(data.getInt(0));
        }

        return dataList;
    }

}

package com.example.eaglefit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ExercisesBacklogQueryHelper {

    private static final String TAG = "ExercisesBacklogQuery";

    private DatabaseHelper databaseHelper;

    private List<TableBuilder> userWorkoutTables;

    public ExercisesBacklogQueryHelper(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public boolean insertNewExerciseToBacklog(String planName, String exerciseName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[0][0], planName);
        contentValues.put(DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[1][0], exerciseName);

        long result = db.insert(DatabaseHelper.TABLE_EXERCISES_BACKLOG, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public void deleteExerciseFromBacklog(String planName, String exerciseName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseHelper.TABLE_EXERCISES_BACKLOG + " WHERE " + DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[0][0] + "='" + planName + "'" + " AND " + DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[1][0] + "='" +  exerciseName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void deletePlanNameFromBacklog(String planName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseHelper.TABLE_EXERCISES_BACKLOG + " WHERE " + DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[0][0] + "='" + planName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public boolean isExerciseOnPlanBacklog(String planName, String exerciseName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "SELECT " + DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[1][0] + " FROM " + DatabaseHelper.TABLE_EXERCISES_BACKLOG + " WHERE " +  DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[0][0] + "='" + planName + "' AND " + DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[1][0] + "='" + exerciseName + "'";
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        if(data.getCount() == 0) return false;
        return true;
    }

    public List<String> grabExercisesFromBacklog(String planName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "SELECT " + DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[1][0] + " FROM " + DatabaseHelper.TABLE_EXERCISES_BACKLOG + " WHERE " +  DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[0][0] + "='" + planName + "'";
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        List<String> dataList = new ArrayList<String>();
        while(data.moveToNext()) {
            dataList.add(data.getString(0));
        }

        return dataList;
    }


    public void updatePlanName(String oldPlanName, String newPlanName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + DatabaseHelper.TABLE_EXERCISES_BACKLOG + " SET " + DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[0][0] + "='" + newPlanName + "' WHERE " +  DatabaseHelper.COLUMNS_EXERCISES_BACKLOG[0][0] + "='" + oldPlanName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }
}

package com.example.eaglefit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserWorkoutsQueryHelper {

    private static final String TAG = "UserWorkoutsQueryHelper";

    private DatabaseHelper databaseHelper;

    private List<TableBuilder> userWorkoutTables;

    public UserWorkoutsQueryHelper(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public boolean insertNewExerciseIntoWorkout(String workoutName, String exerciseName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0], workoutName);
        contentValues.put(DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0], exerciseName);

        long result = db.insert(DatabaseHelper.TABLE_USER_WORKOUTS, null, contentValues);

        if(result == -1) return false;
        return true;
    }

    public void updateExerciseIntoWorkout(String workoutName, String newExercise, String oldExercise) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + DatabaseHelper.TABLE_USER_WORKOUTS + " SET " + DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0] + "='" +  newExercise + "' WHERE " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" + workoutName + "' AND " + DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0] + "='" + oldExercise + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public List<SavedExerciseData> grabExercisesInWorkout(String workoutName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "SELECT " + DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0] + "," + DatabaseHelper.COLUMNS_USER_WORKOUTS[2][0] + "," + DatabaseHelper.COLUMNS_USER_WORKOUTS[3][0] + " FROM " + DatabaseHelper.TABLE_USER_WORKOUTS + " WHERE " +  DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" + workoutName + "'";
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        List<SavedExerciseData> dataList = new ArrayList<SavedExerciseData>();
        while(data.moveToNext()) {
            dataList.add(new SavedExerciseData(data.getString(0), data.getInt(1), data.getInt(2)));
        }

        return dataList;
    }

    public void updateSets(int sets, String workoutName, String exerciseName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + DatabaseHelper.TABLE_USER_WORKOUTS + " SET " + DatabaseHelper.COLUMNS_USER_WORKOUTS[2][0] + "='" +  sets + "' WHERE " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" + workoutName + "' AND " + DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0] + "='" + exerciseName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void updateReps(int reps, String workoutName, String exerciseName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "UPDATE " + DatabaseHelper.TABLE_USER_WORKOUTS + " SET " + DatabaseHelper.COLUMNS_USER_WORKOUTS[3][0] + "='" +  reps + "' WHERE " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" + workoutName + "' AND " + DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0] + "='" + exerciseName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public boolean doesExerciseExistInWorkout(String workoutName, String exerciseName) {
        String query = "SELECT " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + " FROM " + DatabaseHelper.TABLE_USER_WORKOUTS + " WHERE " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" + workoutName + "' AND " + DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0] + "='" + exerciseName + "'";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG

        if(data.getCount() == 0) return false;
        return true;
    }

    public void deleteWorkout(String workoutName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseHelper.TABLE_USER_WORKOUTS + " WHERE " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" +  workoutName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void deleteExercise(String workoutName, String exerciseName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseHelper.TABLE_USER_WORKOUTS + " WHERE " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" +  workoutName + "' AND " + DatabaseHelper.COLUMNS_USER_WORKOUTS[1][0] + "='" + exerciseName + "'";
        db.execSQL(query);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
    }

    public void updateWorkoutPlanName(String oldPlanName, String newPlanName) {
        String[] daysOfTheWeek = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
        for(int i = 0; i < daysOfTheWeek.length; i++) {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            String query = "UPDATE " + DatabaseHelper.TABLE_USER_WORKOUTS + " SET " + DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" + newPlanName + "_" + daysOfTheWeek[i] + "' WHERE " +  DatabaseHelper.COLUMNS_USER_WORKOUTS[0][0] + "='" + oldPlanName + "_" + daysOfTheWeek[i] + "'";
            db.execSQL(query);
            Log.d(TAG, "Executed Query: " + query); //DEBUG
        }
    }
}

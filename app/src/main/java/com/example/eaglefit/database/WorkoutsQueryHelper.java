package com.example.eaglefit.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.example.eaglefit.database.MuscleName;

public class WorkoutsQueryHelper {

    private static final String TAG = "WorkoutsQueryHelper";

    private DatabaseHelper databaseHelper;

    public WorkoutsQueryHelper(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public WorkoutData[] grabExercises(MuscleName muscleName) {
        Cursor data = grabExerciseCursor(getMuscleNameString(muscleName));

        List<WorkoutData> dataList = new ArrayList<WorkoutData>();
        while(data.moveToNext()) {
            dataList.add(new WorkoutData(data.getString(0), data.getString(1)));
        }

        WorkoutData[] dataListArr = new WorkoutData[dataList.size()];
        dataListArr = dataList.toArray(dataListArr);
        return dataListArr;
    }

    private Cursor grabExerciseCursor(String muscleName) {
        String query = "SELECT " + databaseHelper.COLUMNS_WORKOUTS[0][0] + "," + databaseHelper.COLUMNS_WORKOUTS[1][0] + " FROM " + databaseHelper.TABLE_WORKOUTS +" WHERE " + databaseHelper.COLUMNS_WORKOUTS[2][0] + "='" + muscleName + "' OR " + databaseHelper.COLUMNS_WORKOUTS[3][0] + "='" + muscleName + "' OR " + databaseHelper.COLUMNS_WORKOUTS[4][0] + "='" + muscleName + "'";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "Executed Query: " + query); //DEBUG
        return data;
    }

    private String getMuscleNameString(MuscleName muscleName) {
        String muscleNameStr = null;
        switch (muscleName) {
            case Chest: muscleNameStr = "Chest";
                break;
            case Traps: muscleNameStr = "Traps";
                break;
            case Lats: muscleNameStr = "Lats";
                break;
            case LowerBack: muscleNameStr = "LowerBack";
                break;
            case FrontDelts: muscleNameStr = "FrontDelts";
                break;
            case SideDelts: muscleNameStr = "SideDelts";
                break;
            case RearDelts: muscleNameStr = "RearDelts";
                break;
            case Biceps: muscleNameStr = "Biceps";
                break;
            case Triceps: muscleNameStr = "Triceps";
                break;
            case Forearms: muscleNameStr = "Forearms";
                break;
            case Glutes: muscleNameStr = "Glutes";
                break;
            case Quads: muscleNameStr = "Quads";
                break;
            case Hamstrings: muscleNameStr = "Hamstrings";
                break;
            case Calves: muscleNameStr = "Calves";
                break;
            case Abs: muscleNameStr = "Abs";
                break;
        }
        if(muscleNameStr == null) {
            Log.e(TAG, "ERROR: Method 'getMuscleNameString' couldn't find muscle name.");
        }
        return muscleNameStr;
    }

}

package com.example.eaglefit.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedHashMap;

enum MuscleName {
    Chest,
    Traps,
    Lats,
    LowerBack,
    FrontDelts,
    SideDelts,
    RearDelts,
    Biceps,
    Triceps,
    Forearms,
    Glutes,
    Quads,
    Hamstrings,
    Calves,
    Abs
}

enum MuscleCategory {
    Chest,
    Back,
    Shoulders,
    Arms,
    Legs,
    Abs
}

public class WorkoutsQueryHelper {

    private static final String TAG = "WorkoutsQueryHelper";

    private DatabaseHelper databaseHelper;

    /*private final LinkedHashMap<MuscleName, MuscleCategory> muscles = new LinkedHashMap<>() {{
        put(MuscleName.Chest, MuscleCategory.Chest);
        put(MuscleName.Traps, MuscleCategory.Back);
        //Add more here
    }};*/

    public WorkoutsQueryHelper(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    public String[] grabExercises(MuscleName muscleName) {
        String muscleNameStr = getMuscleNameString(muscleName);

        String query = "SELECT * WHERE Muscle_Worked_1='" + muscleNameStr + "' OR Muscle_Worked_2='" + muscleNameStr + "' OR Muscle_Worked_3='" + muscleNameStr + "'";
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor data = db.rawQuery(query, null);

        //TODO: Turn into String[]
        return null;
    }

    public String[] grabExercises(MuscleCategory muscleCategory) {

        return null;
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

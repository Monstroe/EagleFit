package com.example.eaglefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.eaglefit.database.WorkoutData;
import com.example.eaglefit.fragments.muscle.SearchFragment;
import com.example.eaglefit.fragments.workout.WorkoutListFragment;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan);

        //Grabbing data from MuscleChartFragment
        Intent intent = getIntent();
        String planName = intent.getStringExtra("Name");

        Bundle bundle = new Bundle();
        bundle.putString("Name", planName);

        //Create new instance of SearchFragment
        WorkoutListFragment fragment = new WorkoutListFragment();
        //Send the exercise data to SearchFragment
        fragment.setArguments(bundle);

        //Set fragment as view
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
    }
}
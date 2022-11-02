package com.example.eaglefit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.eaglefit.database.WorkoutData;
import com.example.eaglefit.fragments.muscle.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MuscleSearchActivity extends AppCompatActivity {

    private static final String TAG = "MuscleSearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_search);

        //Grabbing data from MuscleChartFragment
        Intent intent = getIntent();
        List<WorkoutData> exercises = intent.getParcelableArrayListExtra("Exercises");
        //Preparing data from MuscleChartFragment to be sent to SearchFragment
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("Exercises", (ArrayList<? extends Parcelable>) exercises);

        //Create new instance of SearchFragment
        SearchFragment fragment = new SearchFragment();
        //Send the exercise data to SearchFragment
        fragment.setArguments(bundle);

        //Set fragment as view
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
    }

}
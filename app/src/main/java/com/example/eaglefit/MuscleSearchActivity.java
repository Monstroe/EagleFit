package com.example.eaglefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.eaglefit.database.WorkoutData;

public class MuscleSearchActivity extends AppCompatActivity {

    private static final String TAG = "MuscleSearchFragment";

    private ListView lvMuscleSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_search);

        lvMuscleSearch = (ListView) findViewById(R.id.muscle_search_listView);

        populateListView();
    }

    private void populateListView() {
        Intent intent = getIntent();
        WorkoutData[] exercises = (WorkoutData[]) intent.getParcelableArrayExtra("Exercises");

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);
        lvMuscleSearch.setAdapter(adapter);
    }
}
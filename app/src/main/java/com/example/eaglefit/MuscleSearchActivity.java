package com.example.eaglefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.eaglefit.database.WorkoutData;

public class MuscleSearchActivity extends AppCompatActivity {

    private static final String TAG = "MuscleSearchFragment";

    private WorkoutData[] exercises;

    private ListView lvMuscleSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_search);

        lvMuscleSearch = (ListView) findViewById(R.id.muscle_search_listView);
        populateListView();
        addEventToListView();

    }

    private void addEventToListView() {
        lvMuscleSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemName = (String) adapterView.getItemAtPosition(position);
                for(WorkoutData exercise : exercises) {
                    if(exercise.getExerciseName().equals(selectedItemName)) {
                        Bundle bundle = new Bundle();
                        bundle.putString("ExerciseName", exercise.getExerciseName());
                        bundle.putString("ExerciseDesc", exercise.getExerciseDescription());
                        //Navigate to fragment
                    }
                }
            }
        });
    }

    private void populateListView() {
        Intent intent = getIntent();
        exercises = (WorkoutData[]) intent.getParcelableArrayExtra("Exercises");

        String[] exerciseNames = new String[exercises.length];
        for(int i = 0; i < exerciseNames.length; i++) {
            exerciseNames[i] = exercises[i].getExerciseName();
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseNames);
        lvMuscleSearch.setAdapter(adapter);
    }

}
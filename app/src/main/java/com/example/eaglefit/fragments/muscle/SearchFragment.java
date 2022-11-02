package com.example.eaglefit.fragments.muscle;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.eaglefit.R;
import com.example.eaglefit.database.WorkoutData;

import java.util.List;

public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    private List<WorkoutData> exercises;

    private ListView lvMuscleSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            exercises = getArguments().getParcelableArrayList("Exercises");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        lvMuscleSearch = (ListView) view.findViewById(R.id.muscle_search_listView);
        populateListView();
        addEventToListView();

        // Inflate the layout for this fragment
        return view;
    }

    private void addEventToListView() {
        lvMuscleSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemName = (String) adapterView.getItemAtPosition(position);
                for(WorkoutData exercise : exercises) {
                    if(exercise.getExerciseName().equals(selectedItemName)) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("Exercise", (Parcelable) exercise);
                        ExerciseFragment fragment = new ExerciseFragment();
                        fragment.setArguments(bundle);
                        //Switch to exercise fragment
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
                    }
                }
            }
        });
    }

    private void populateListView() {
        String[] exerciseNames = new String[exercises.size()];
        for(int i = 0; i < exerciseNames.length; i++) {
            exerciseNames[i] = exercises.get(i).getExerciseName();
        }

        ListAdapter adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, exerciseNames);
        lvMuscleSearch.setAdapter(adapter);
    }

}
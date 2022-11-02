package com.example.eaglefit.fragments.muscle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eaglefit.R;
import com.example.eaglefit.database.WorkoutData;

import org.w3c.dom.Text;

public class ExerciseFragment extends Fragment {

    private static final String TAG = "ExerciseFragment";

    private WorkoutData exercise;

    private TextView name;
    private TextView description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            exercise = getArguments().getParcelable("Exercise");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        name = view.findViewById(R.id.exercise_name);
        description = view.findViewById(R.id.exercise_description);

        name.setText(exercise.getExerciseName());
        description.setText(exercise.getExerciseDescription());

        // Inflate the layout for this fragment
        return view;
    }
}
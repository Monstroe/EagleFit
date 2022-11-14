package com.example.eaglefit.fragments.workout;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.eaglefit.R;


public class WorkoutEditFragment extends Fragment {

    private Button addNewExerciseBtn;
    private LinearLayout exerciseLl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_edit, container, false);

        exerciseLl = (LinearLayout) view.findViewById(R.id.ll_added_workouts);

        addNewExerciseBtn = (Button) view.findViewById(R.id.btn_add_new_exercise);
        addNewExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.layout_exercise_in_workout, null);
                exerciseLl.addView(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
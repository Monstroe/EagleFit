package com.example.eaglefit.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.eaglefit.MuscleSearchActivity;
import com.example.eaglefit.R;
import com.example.eaglefit.database.MuscleName;
import com.example.eaglefit.database.WorkoutsQueryHelper;

public class MuscleChartFragment extends Fragment {

    private WorkoutsQueryHelper workoutsQueryHelper;

    private Button btnMuscleSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muscle_chart, container, false);

        workoutsQueryHelper = new WorkoutsQueryHelper(view.getContext());

        btnMuscleSearch = (Button) view.findViewById(R.id.muscle_search_btn);
        btnMuscleSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] exercises = workoutsQueryHelper.grabExercises(MuscleName.Chest);
                Intent intent = new Intent(getActivity(), MuscleSearchActivity.class);
                intent.putExtra("Exercises", exercises);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}
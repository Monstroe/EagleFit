package com.example.eaglefit.fragments.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.eaglefit.MuscleSearchActivity;
import com.example.eaglefit.R;
import com.example.eaglefit.database.MuscleName;
import com.example.eaglefit.database.WorkoutData;
import com.example.eaglefit.database.WorkoutsQueryHelper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MuscleChartFragment extends Fragment {

    private static final String TAG = "MuscleChartFragment";

    private WorkoutsQueryHelper workoutsQueryHelper;

    private LinkedHashMap<Button, MuscleName> muscleChartButtons;

    // Variables for switch, images, buttons, etc.
    private SwitchCompat chartFlipper;
    private ImageView frontIm;
    private ImageView backIm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_muscle_chart, container, false); //<-- Don't worry about this

        //Initializing WorkoutQueryHelper Class. This class is used to search through the database.
        workoutsQueryHelper = new WorkoutsQueryHelper(view.getContext());
        //ArrayList holding all of the buttons on the Muscle Man
        muscleChartButtons = new LinkedHashMap<>();
        addButtonsToList(view);
        addEventsToButtons();

        //Switch properties and actions ISSUE IS HERE
//        chartFlipper = (SwitchCompat) chartFlipper.findViewById(R.id.chartFlipper);
//        frontIm = (ImageView) frontIm.findViewById(R.id.imageFront);
//        backIm = (ImageView) backIm.findViewById(R.id.imageBack);
//        Boolean switchState = chartFlipper.isChecked();
//
//        if(switchState){
//            frontIm.setVisibility(View.VISIBLE);
//            backIm.setVisibility(View.INVISIBLE);
//        }else{
//            frontIm.setVisibility((View.INVISIBLE));
//            backIm.setVisibility(View.VISIBLE);
//        }

        // Inflate the layout for this fragment
        return view;
    }

    private void addEventsToButtons() {
        for (Button button : muscleChartButtons.keySet()) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<WorkoutData> exercises = workoutsQueryHelper.grabExercises(muscleChartButtons.get(button));
                    Intent intent = new Intent(getActivity(), MuscleSearchActivity.class);
                    intent.putParcelableArrayListExtra("Exercises", (ArrayList<? extends Parcelable>) exercises);
                    startActivity(intent);
                }
            });
        }
    }

    private void addButtonsToList(View view) {
        muscleChartButtons.put((Button) view.findViewById(R.id.muscle_search_btn), MuscleName.Chest); //TEMP: For testing
        //TODO: Add buttons to list
    }
}
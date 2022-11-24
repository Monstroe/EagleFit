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

        //Front Side Buttons
        muscleChartButtons.put((Button) view.findViewById(R.id.FShoulderButton), MuscleName.FrontDelts);
        muscleChartButtons.put((Button) view.findViewById(R.id.BicepButton), MuscleName.Biceps);
        muscleChartButtons.put((Button) view.findViewById(R.id.FForearmButton), MuscleName.Forearms);
        muscleChartButtons.put((Button) view.findViewById(R.id.PecButton), MuscleName.Chest);
        muscleChartButtons.put((Button) view.findViewById(R.id.AbsButton), MuscleName.Abs);
        muscleChartButtons.put((Button) view.findViewById(R.id.ObliqueButton), MuscleName.Oblique);
        muscleChartButtons.put((Button) view.findViewById(R.id.QuadButton), MuscleName.Quads);
        muscleChartButtons.put((Button) view.findViewById(R.id.GroinButton), MuscleName.Groin);

        //Back Side Buttons
        muscleChartButtons.put((Button) view.findViewById(R.id.BShoulderButton), MuscleName.RearDelts);
        muscleChartButtons.put((Button) view.findViewById(R.id.TrapButton), MuscleName.Traps);
        muscleChartButtons.put((Button) view.findViewById(R.id.ScapButton), MuscleName.Scaps);
        muscleChartButtons.put((Button) view.findViewById(R.id.LatButton), MuscleName.Lats);
        muscleChartButtons.put((Button) view.findViewById(R.id.GluteButton), MuscleName.Glutes);
        muscleChartButtons.put((Button) view.findViewById(R.id.HamstringButton), MuscleName.Hamstrings);
        muscleChartButtons.put((Button) view.findViewById(R.id.CalfButton), MuscleName.Calves);

    }
}
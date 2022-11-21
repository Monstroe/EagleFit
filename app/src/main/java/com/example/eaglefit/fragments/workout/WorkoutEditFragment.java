package com.example.eaglefit.fragments.workout;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eaglefit.R;
import com.example.eaglefit.database.ExercisesBacklogQueryHelper;
import com.example.eaglefit.database.PlansQueryHelper;
import com.example.eaglefit.database.SavedExerciseData;
import com.example.eaglefit.database.UserWorkoutsQueryHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class WorkoutEditFragment extends Fragment {

    private static final String TAG = "WorkoutEditFragment";

    private String workoutName;
    private String planName;
    private int dayOfTheWeek;
    private UserWorkoutsQueryHelper userWorkoutsQueryHelper;
    private ExercisesBacklogQueryHelper exercisesBacklogQueryHelper;
    private PlansQueryHelper plansQueryHelper;
    private List<SavedExerciseData> exerciseData;

    private Button addNewExerciseBtn;
    private Button deleteWorkoutBtn;
    private LinearLayout exerciseLl;

    private List<Spinner> exercisesSpns;
    private List<EditText> setsEts;
    private List<EditText> repsEts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workoutName = getArguments().getString("WorkoutName");
            planName = getArguments().getString("PlanName");
            dayOfTheWeek = getArguments().getInt("Day");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_edit, container, false);

        userWorkoutsQueryHelper = new UserWorkoutsQueryHelper(view.getContext());
        exercisesBacklogQueryHelper = new ExercisesBacklogQueryHelper(view.getContext());
        plansQueryHelper = new PlansQueryHelper(view.getContext());

        exerciseLl = (LinearLayout) view.findViewById(R.id.ll_added_workouts);

        exercisesSpns = new ArrayList<Spinner>();
        setsEts = new ArrayList<EditText>();
        repsEts = new ArrayList<EditText>();

        addExercisesFromDatabase(view);

        addNewExerciseBtn = (Button) view.findViewById(R.id.btn_add_new_exercise);
        deleteWorkoutBtn = (Button) view.findViewById(R.id.btn_delete_workout);

        addNewExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseData.add(new SavedExerciseData());
                createSpinnerMenu(view);
            }
        });

        deleteWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userWorkoutsQueryHelper.deleteWorkout(workoutName);
                plansQueryHelper.deleteWorkoutFromPlan(planName, dayOfTheWeek);

                Bundle bundle = new Bundle();
                bundle.putString("Name", planName);
                WorkoutListFragment fragment = new WorkoutListFragment();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void addExercisesFromDatabase(View view) {
        exerciseData = userWorkoutsQueryHelper.grabExercisesInWorkout(workoutName);

        for(SavedExerciseData data : exerciseData) {
            View exerciseV = createSpinnerMenu(view);

            EditText setsEt = (EditText) exerciseV.findViewById(R.id.et_sets);
            setsEt.setText(String.valueOf(data.getSets()));

            EditText repsEt = (EditText) exerciseV.findViewById(R.id.et_reps);
            repsEt.setText(String.valueOf(data.getReps()));
        }
    }

    private View createSpinnerMenu(View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.layout_exercise_in_workout, null);
        exerciseLl.addView(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        Spinner spinner = (Spinner) v.findViewById(R.id.spn_exercise_name);
        EditText setsEt = (EditText) v.findViewById(R.id.et_sets);
        EditText repsEt = (EditText) v.findViewById(R.id.et_reps);

        exercisesSpns.add(spinner);
        setsEts.add((EditText) v.findViewById(R.id.et_sets));
        repsEts.add((EditText) v.findViewById(R.id.et_reps));

        List<String> exercises = exercisesBacklogQueryHelper.grabExercisesFromBacklog(planName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, exercises);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemName = (String) adapterView.getItemAtPosition(position);
                if(!userWorkoutsQueryHelper.doesExerciseExistInWorkout(workoutName, exerciseData.get(exercisesSpns.indexOf(spinner)).getExerciseName())) {
                    userWorkoutsQueryHelper.insertNewExerciseIntoWorkout(workoutName, selectedItemName);
                }
                else {
                    userWorkoutsQueryHelper.updateExerciseIntoWorkout(workoutName, selectedItemName, exerciseData.get(exercisesSpns.indexOf(spinner)).getExerciseName());
                }
                exerciseData.get(exercisesSpns.indexOf(spinner)).setExerciseName(selectedItemName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //NOTHING
            }
        });
        setsEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    if(Integer.parseInt(String.valueOf(setsEt.getText())) != exerciseData.get(setsEts.indexOf(setsEt)).getSets()) {
                        userWorkoutsQueryHelper.updateSets(Integer.parseInt(String.valueOf(setsEt.getText())), workoutName, exerciseData.get(setsEts.indexOf(setsEt)).getExerciseName());
                        exerciseData.get(setsEts.indexOf(setsEt)).setSets(Integer.parseInt(String.valueOf(setsEt.getText())));
                    }
                }
            }
        });
        repsEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    if(Integer.parseInt(String.valueOf(repsEt.getText())) != exerciseData.get(repsEts.indexOf(repsEt)).getReps()) {
                        userWorkoutsQueryHelper.updateReps(Integer.parseInt(String.valueOf(repsEt.getText())), workoutName, exerciseData.get(repsEts.indexOf(repsEt)).getExerciseName());
                        exerciseData.get(repsEts.indexOf(repsEt)).setReps(Integer.parseInt(String.valueOf(repsEt.getText())));
                    }
                }
            }
        });

        return v;
    }

}
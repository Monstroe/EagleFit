package com.example.eaglefit.fragments.muscle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eaglefit.R;
import com.example.eaglefit.database.ExercisesBacklogQueryHelper;
import com.example.eaglefit.database.PlansQueryHelper;
import com.example.eaglefit.database.WorkoutData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends Fragment {

    private static final String TAG = "ExerciseFragment";

    private WorkoutData exercise;
    private PlansQueryHelper plansQueryHelper;
    private ExercisesBacklogQueryHelper exercisesBacklogQueryHelper;

    private TextView nameTv;
    private TextView descriptionTv;
    private Button addToPlanBtn;
    private Button removeFromPlanBtn;

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

        plansQueryHelper = new PlansQueryHelper(view.getContext());
        exercisesBacklogQueryHelper = new ExercisesBacklogQueryHelper(view.getContext());

        nameTv = view.findViewById(R.id.tv_exercise_name);
        descriptionTv = view.findViewById(R.id.tv_exercise_desc);

        nameTv.setText(exercise.getExerciseName());

        String[] muscleFullNames = exercise.getExerciseDescription().split(",");
        String description = "Works out ";
        if(muscleFullNames.length == 1)
            description += muscleFullNames[0];
        else if(muscleFullNames.length == 2)
            description += muscleFullNames[0] + " and " + muscleFullNames[1];
        else
            description += muscleFullNames[0] + ", " + muscleFullNames[1] + ", and " + muscleFullNames[2];
        description += ".";

        descriptionTv.setText(description);

        addToPlanBtn = (Button) view.findViewById(R.id.btn_add_to_plan);
        addToPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), addToPlanBtn);
                ArrayList<String> workoutPlans = plansQueryHelper.grabWorkoutPlanNames();
                for (String planName : workoutPlans) {
                    popupMenu.getMenu().add(planName);
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String selectedPlan = menuItem.getTitle().toString();
                        if(!exercisesBacklogQueryHelper.isExerciseOnPlanBacklog(selectedPlan, exercise.getExerciseName())) {
                            exercisesBacklogQueryHelper.insertNewExerciseToBacklog(selectedPlan, exercise.getExerciseName());
                            Toast toast = Toast.makeText(view.getContext(), "Added to workout plan", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            Toast toast = Toast.makeText(view.getContext(), "Exercise already on workout plan!", Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        removeFromPlanBtn = (Button) view.findViewById(R.id.btn_remove_from_plan);
        removeFromPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), removeFromPlanBtn);
                ArrayList<String> workoutPlans = plansQueryHelper.grabWorkoutPlanNames();
                for(String plan : workoutPlans) {
                    if(exercisesBacklogQueryHelper.isExerciseOnPlanBacklog(plan, exercise.getExerciseName())) {
                        popupMenu.getMenu().add(plan);
                    }
                }

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String selectedPlan = menuItem.getTitle().toString();
                        exercisesBacklogQueryHelper.deleteExerciseFromBacklog(selectedPlan, exercise.getExerciseName());
                        Toast toast = Toast.makeText(view.getContext(), "Removed from workout plan", Toast.LENGTH_SHORT);
                        toast.show();
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
package com.example.eaglefit.fragments.workout;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eaglefit.MainActivity;
import com.example.eaglefit.MuscleSearchActivity;
import com.example.eaglefit.R;
import com.example.eaglefit.database.ExercisesBacklogQueryHelper;
import com.example.eaglefit.database.PlansQueryHelper;
import com.example.eaglefit.database.SavedExerciseData;
import com.example.eaglefit.database.UserWorkoutsQueryHelper;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListFragment extends Fragment {

   private String planName;
   private PlansQueryHelper plansQueryHelper;
   private UserWorkoutsQueryHelper userWorkoutsQueryHelper;
   private ExercisesBacklogQueryHelper exercisesBacklogQueryHelper;

   private EditText planNameEt;
   private Button planRenameBtn;
   private Button planDeleteBtn;
   private Button setActiveBtn;

   private ArrayList<Button> newWorkoutBtns;
   private ArrayList<CheckBox> restDayCbs;
   private ArrayList<Button> editWorkoutBtns;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            planName = getArguments().getString("Name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_list, container, false);

        plansQueryHelper = new PlansQueryHelper(view.getContext());
        userWorkoutsQueryHelper = new UserWorkoutsQueryHelper(view.getContext());
        exercisesBacklogQueryHelper = new ExercisesBacklogQueryHelper(view.getContext());

        planNameEt = view.findViewById(R.id.et_rename);
        planNameEt.setText(planName);

        planRenameBtn = (Button) view.findViewById(R.id.btn_rename);
        planDeleteBtn = (Button) view.findViewById(R.id.btn_delete);
        setActiveBtn = (Button) view.findViewById(R.id.btn_set_active);

        planRenameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plansQueryHelper.updateWorkoutPlanName(planName, String.valueOf(planNameEt.getText()));
                userWorkoutsQueryHelper.updateWorkoutPlanName(planName, String.valueOf(planNameEt.getText()));
                exercisesBacklogQueryHelper.updatePlanName(planName, String.valueOf(planNameEt.getText()));
                planName = String.valueOf(planNameEt.getText());
                Toast toast = Toast.makeText(view.getContext(), "Plan name updated", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        planDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plansQueryHelper.deleteWorkoutPlan(planName);
                exercisesBacklogQueryHelper.deletePlanNameFromBacklog(planName);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                //TODO: Pass data to intent
                startActivity(intent);
            }
        });
        setActiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plansQueryHelper.setActivePlan(planName);
                Toast toast = Toast.makeText(view.getContext(), "Set as active plan", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        newWorkoutBtns = new ArrayList<Button>();
        addNewWorkoutButtons(view);
        addEventsToNewWorkoutButtons();
        restDayCbs = new ArrayList<CheckBox>();
        addRestDayCheckBoxes(view);
        addEventsToRestDayCheckBoxes();
        editWorkoutBtns = new ArrayList<Button>();
        addEditWorkoutButtons(view);
        addEventsToEditWorkoutButtons();

        updateVisibilityOnGUI();


        // Inflate the layout for this fragment
        return view;
    }

    private void addEventsToNewWorkoutButtons() {
        for (Button button : newWorkoutBtns) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button.setVisibility(View.INVISIBLE);
                    editWorkoutBtns.get(newWorkoutBtns.indexOf(button)).setVisibility(View.VISIBLE);

                    plansQueryHelper.updateWorkoutDay(planName, newWorkoutBtns.indexOf(button));

                    Bundle bundle = new Bundle();
                    bundle.putString("WorkoutName", generateWorkoutName(newWorkoutBtns.indexOf(button)));
                    bundle.putString("PlanName", planName);
                    bundle.putInt("Day", newWorkoutBtns.indexOf(button));
                    WorkoutEditFragment fragment = new WorkoutEditFragment();
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
                }
            });
        }
    }

    private void addEventsToEditWorkoutButtons() {
        for (Button button : editWorkoutBtns) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("WorkoutName", generateWorkoutName(editWorkoutBtns.indexOf(button)));
                    bundle.putString("PlanName", planName);
                    bundle.putInt("Day", editWorkoutBtns.indexOf(button));
                    WorkoutEditFragment fragment = new WorkoutEditFragment();
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
                }
            });
        }
    }

    private void addEventsToRestDayCheckBoxes() {
        for(CheckBox checkBox : restDayCbs) {
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleCheckedRestDay(checkBox);
                }
            });
        }
    }

    private void toggleCheckedRestDay(CheckBox checkBox) {
        if(checkBox.isChecked()) {
            newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setClickable(false);
            newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setAlpha(0.25f);

            editWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setClickable(false);
            editWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setAlpha(0.25f);

            plansQueryHelper.setWorkoutAsRestDay(planName, restDayCbs.indexOf(checkBox));
        }
        else {
            newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setClickable(true);
            newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setAlpha(1.0f);

            editWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setClickable(true);
            editWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setAlpha(1.0f);

            List<SavedExerciseData> temp = userWorkoutsQueryHelper.grabExercisesInWorkout(planName + "_" + plansQueryHelper.getDayOfTheWeek(restDayCbs.indexOf(checkBox)));
            if(temp.size() == 0)
                plansQueryHelper.deleteWorkoutFromPlan(planName, restDayCbs.indexOf(checkBox));
            else
                plansQueryHelper.updateWorkoutDay(planName, restDayCbs.indexOf(checkBox));
        }
    }

    private void updateVisibilityOnGUI() {
        for(Button button : newWorkoutBtns) {
            if(plansQueryHelper.doesWorkoutExist(planName, newWorkoutBtns.indexOf(button))) {
                button.setVisibility(View.INVISIBLE);
                editWorkoutBtns.get(newWorkoutBtns.indexOf(button)).setVisibility(View.VISIBLE);
            }
        }
        boolean[] restDays = plansQueryHelper.grabRestDays(planName);
        for(int i = 0; i < restDays.length; i++) {
            restDayCbs.get(i).setChecked(restDays[i]);
            if(restDays[i]) {
                newWorkoutBtns.get(i).setClickable(false);
                newWorkoutBtns.get(i).setAlpha(0.25f);

                editWorkoutBtns.get(i).setClickable(false);
                editWorkoutBtns.get(i).setAlpha(0.25f);
            }
        }
    }

    private void addNewWorkoutButtons(View view) {
        newWorkoutBtns.add((Button) view.findViewById(R.id.btn_new_workout_sun));
        newWorkoutBtns.add((Button) view.findViewById(R.id.btn_new_workout_mon));
        newWorkoutBtns.add((Button) view.findViewById(R.id.btn_new_workout_tues));
        newWorkoutBtns.add((Button) view.findViewById(R.id.btn_new_workout_wed));
        newWorkoutBtns.add((Button) view.findViewById(R.id.btn_new_workout_thurs));
        newWorkoutBtns.add((Button) view.findViewById(R.id.btn_new_workout_fri));
        newWorkoutBtns.add((Button) view.findViewById(R.id.btn_new_workout_sat));
    }
    private void addRestDayCheckBoxes(View view) {
        restDayCbs.add((CheckBox) view.findViewById(R.id.cb_rest_day_sun));
        restDayCbs.add((CheckBox) view.findViewById(R.id.cb_rest_day_mon));
        restDayCbs.add((CheckBox) view.findViewById(R.id.cb_rest_day_tues));
        restDayCbs.add((CheckBox) view.findViewById(R.id.cb_rest_day_wed));
        restDayCbs.add((CheckBox) view.findViewById(R.id.cb_rest_day_thurs));
        restDayCbs.add((CheckBox) view.findViewById(R.id.cb_rest_day_fri));
        restDayCbs.add((CheckBox) view.findViewById(R.id.cb_rest_day_sat));
    }
    private void addEditWorkoutButtons(View view) {
        editWorkoutBtns.add((Button) view.findViewById(R.id.btn_edit_workout_sun));
        editWorkoutBtns.add((Button) view.findViewById(R.id.btn_edit_workout_mon));
        editWorkoutBtns.add((Button) view.findViewById(R.id.btn_edit_workout_tues));
        editWorkoutBtns.add((Button) view.findViewById(R.id.btn_edit_workout_wed));
        editWorkoutBtns.add((Button) view.findViewById(R.id.btn_edit_workout_thurs));
        editWorkoutBtns.add((Button) view.findViewById(R.id.btn_edit_workout_fri));
        editWorkoutBtns.add((Button) view.findViewById(R.id.btn_edit_workout_sat));
    }

    private String generateWorkoutName(int dayOfTheWeek) {
        return planName + "_" + plansQueryHelper.getDayOfTheWeek(dayOfTheWeek);
    }

}
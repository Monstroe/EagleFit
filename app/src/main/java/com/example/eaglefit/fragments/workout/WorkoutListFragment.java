package com.example.eaglefit.fragments.workout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eaglefit.R;
import com.example.eaglefit.database.PlansQueryHelper;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListFragment extends Fragment {

   private String planName;
   private PlansQueryHelper plansQueryHelper;

   private EditText planNameEt;
   private Button planRenameBtn;
   private Button planDeleteBtn;

   private ArrayList<Button> newWorkoutBtns;
   private ArrayList<CheckBox> restDayCbs;


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

        planNameEt = view.findViewById(R.id.et_rename);
        planNameEt.setText(planName);

        planRenameBtn = (Button) view.findViewById(R.id.btn_rename);
        planDeleteBtn = (Button) view.findViewById(R.id.btn_delete);

        planRenameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plansQueryHelper.updateWorkoutPlanName(planName, String.valueOf(planNameEt.getText()));
                planName = String.valueOf(planNameEt.getText());
                Toast toast = Toast.makeText(view.getContext(), "Plan name updated", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        planDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete plan from database
                //Go back to workout fragment
            }
        });

        newWorkoutBtns = new ArrayList<Button>();
        addWorkoutButtons(view);
        addEventsToNewWorkoutButtons();
        restDayCbs = new ArrayList<CheckBox>();
        addRestDayCheckBoxes(view);
        addEventsToRestDayCheckBoxes();


        // Inflate the layout for this fragment
        return view;
    }

    private void addEventsToNewWorkoutButtons() {
        for (Button button : newWorkoutBtns) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WorkoutEditFragment fragment = new WorkoutEditFragment();
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
                    if(checkBox.isChecked()) {
                        newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setClickable(false);
                        newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setAlpha(0.25f);
                    }
                    else {
                        newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setClickable(true);
                        newWorkoutBtns.get(restDayCbs.indexOf(checkBox)).setAlpha(1.0f);
                    }

                }
            });
        }
    }

    private void addWorkoutButtons(View view) {
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

}
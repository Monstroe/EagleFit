package com.example.eaglefit.fragments.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.eaglefit.R;
import com.example.eaglefit.StartWorkoutActivity;
import com.example.eaglefit.WorkoutPlanActivity;
import com.example.eaglefit.database.PlansQueryHelper;
import com.example.eaglefit.database.UserWorkoutsQueryHelper;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private int dayOfWeekInt;
    private String dayOfWeekStr;

    private PlansQueryHelper plansQueryHelper;
    private UserWorkoutsQueryHelper userWorkoutsQueryHelper;

    private Button startWorkoutBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int dayOfWeekInt = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeekStr = "";
        switch (dayOfWeekInt) {
            case 1: dayOfWeekStr = "SUNDAY";
                break;
            case 2: dayOfWeekStr = "MONDAY";
                break;
            case 3: dayOfWeekStr = "TUESDAY";
                break;
            case 4: dayOfWeekStr = "WEDNESDAY";
                break;
            case 5: dayOfWeekStr = "THURSDAY";
                break;
            case 6: dayOfWeekStr = "FRIDAY";
                break;
            case 7: dayOfWeekStr = "SATURDAY";
                break;
            default: Log.d(TAG, "INVALID DAY OF WEEK: " + dayOfWeekInt);
                break;
        }

        plansQueryHelper = new PlansQueryHelper(getContext());
        userWorkoutsQueryHelper = new UserWorkoutsQueryHelper(getContext());

        startWorkoutBtn = (Button) view.findViewById(R.id.btn_start_workout);

        if(userWorkoutsQueryHelper.grabExercisesInWorkout(plansQueryHelper.getActivePlan() + "_" + dayOfWeekStr).size() == 0) {
            startWorkoutBtn.setClickable(false);
            startWorkoutBtn.setAlpha(0.25f);
        }

        startWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StartWorkoutActivity.class);
                intent.putExtra("Day", dayOfWeekStr);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}
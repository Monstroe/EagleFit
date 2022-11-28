package com.example.eaglefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eaglefit.database.PlansQueryHelper;
import com.example.eaglefit.database.SavedExerciseData;
import com.example.eaglefit.database.UserWorkoutsQueryHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class StartWorkoutActivity extends AppCompatActivity {

    private static final String TAG = "StartWorkoutActivity";

    private int dayOfWeekInt;
    private String dayOfWeekStr;
    private List<View> wrLayouts;
    private List<SavedExerciseData> exerciseData;

    private UserWorkoutsQueryHelper userWorkoutsQueryHelper;
    private PlansQueryHelper plansQueryHelper;

    private LinearLayout exerciseLl;
    private TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        Intent intent = getIntent();
        dayOfWeekInt = intent.getIntExtra("Day", -1);
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

        userWorkoutsQueryHelper = new UserWorkoutsQueryHelper(getBaseContext());
        plansQueryHelper = new PlansQueryHelper(getBaseContext());

        exerciseLl = (LinearLayout) findViewById(R.id.ll_exercises);
        titleTv = (TextView) findViewById(R.id.tv_day_of_the_week);
        titleTv.setText(dayOfWeekStr);

        wrLayouts = new ArrayList<View>();
        exerciseData = userWorkoutsQueryHelper.grabExercisesInWorkout(plansQueryHelper.getActivePlan() + "_" + dayOfWeekStr);
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(SavedExerciseData data : exerciseData) {
            TextView exerciseNameTv = new TextView(getBaseContext());
            exerciseNameTv.setText(data.getExerciseName());
            exerciseNameTv.setTextSize(18);
            exerciseLl.addView(exerciseNameTv, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            for(int i = 0; i < data.getSets(); i++) {
                View v = inflater.inflate(R.layout.layout_exercise_stats, null);
                exerciseLl.addView(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                wrLayouts.add(v);
            }
        }

    }
}
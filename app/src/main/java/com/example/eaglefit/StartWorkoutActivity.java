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
        dayOfWeekStr = intent.getStringExtra("Day");

        userWorkoutsQueryHelper = new UserWorkoutsQueryHelper(getBaseContext());
        plansQueryHelper = new PlansQueryHelper(getBaseContext());

        exerciseLl = (LinearLayout) findViewById(R.id.ll_exercises);
        titleTv = (TextView) findViewById(R.id.tv_day_of_the_week);
        titleTv.setText(dayOfWeekStr);

        wrLayouts = new ArrayList<View>();
        exerciseData = userWorkoutsQueryHelper.grabExercisesInWorkout(plansQueryHelper.getActivePlan() + "_" + dayOfWeekStr);
        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(SavedExerciseData data : exerciseData) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, 32, 0, 0);
            TextView exerciseNameTv = new TextView(getBaseContext());
            exerciseNameTv.setText(data.getExerciseName());
            exerciseNameTv.setTextSize(20);
            exerciseNameTv.setLayoutParams(layoutParams);
            exerciseLl.addView(exerciseNameTv);
            for(int i = 0; i < data.getSets(); i++) {
                View v = inflater.inflate(R.layout.layout_exercise_stats, null);
                exerciseLl.addView(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                wrLayouts.add(v);
            }
        }

    }
}
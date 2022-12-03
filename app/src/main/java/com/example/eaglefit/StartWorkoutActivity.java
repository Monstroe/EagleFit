package com.example.eaglefit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eaglefit.database.PlansQueryHelper;
import com.example.eaglefit.database.SavedExerciseData;
import com.example.eaglefit.database.UserProgressQueryHelper;
import com.example.eaglefit.database.UserWorkoutsQueryHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class StartWorkoutActivity extends AppCompatActivity {

    private static final String TAG = "StartWorkoutActivity";

    private String dayOfWeekStr;
    private LinkedHashMap<View, String> wrLayouts;
    private List<SavedExerciseData> exerciseData;

    private UserWorkoutsQueryHelper userWorkoutsQueryHelper;
    private PlansQueryHelper plansQueryHelper;
    private UserProgressQueryHelper userProgressQueryHelper;

    private LinearLayout exerciseLl;
    private TextView titleTv;
    private Button finsihBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_workout);

        Intent intent = getIntent();
        dayOfWeekStr = intent.getStringExtra("Day");

        userWorkoutsQueryHelper = new UserWorkoutsQueryHelper(getBaseContext());
        plansQueryHelper = new PlansQueryHelper(getBaseContext());
        userProgressQueryHelper = new UserProgressQueryHelper(getBaseContext());

        exerciseLl = (LinearLayout) findViewById(R.id.ll_exercises);
        titleTv = (TextView) findViewById(R.id.tv_day_of_the_week);
        titleTv.setText(dayOfWeekStr);

        wrLayouts = new LinkedHashMap<View, String>();
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
                wrLayouts.put(v, String.valueOf(exerciseNameTv.getText()));
            }
        }

        finsihBtn = (Button) findViewById(R.id.btn_finish_workout);
        finsihBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(SavedExerciseData data : exerciseData) {
                    if(data.getExerciseName().equals("Flat Bench Press")) {
                        Log.d("Hello", "Bench");
                        View v = getMaxWeightView("Flat Bench Press");
                        userProgressQueryHelper.insertWorkoutData("Flat Bench Press", Integer.parseInt(String.valueOf(((EditText) v.findViewById(R.id.et_weight)).getText())), Integer.parseInt(String.valueOf(((EditText) v.findViewById(R.id.et_reps)).getText())));
                    }
                    if(data.getExerciseName().equals("Back Squat")) {
                        Log.d("Hello", "Squat");
                        View v = getMaxWeightView("Back Squat");
                        userProgressQueryHelper.insertWorkoutData("BackSquat", Integer.parseInt(String.valueOf(((EditText) v.findViewById(R.id.et_weight)).getText())), Integer.parseInt(String.valueOf(((EditText) v.findViewById(R.id.et_reps)).getText())));
                    }
                    if(data.getExerciseName().equals("Barbell Deadlift")) {
                        Log.d("Hello", "Deadlift");
                        View v = getMaxWeightView("Barbell Deadlift");
                        userProgressQueryHelper.insertWorkoutData("Barbell Deadlift", Integer.parseInt(String.valueOf(((EditText) v.findViewById(R.id.et_weight)).getText())), Integer.parseInt(String.valueOf(((EditText) v.findViewById(R.id.et_reps)).getText())));
                    }
                }

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public View getMaxWeightView(String exerciseName) {
        List<View> maxViews = new ArrayList<View>();
        for(View v : wrLayouts.keySet()) {
            if(wrLayouts.get(v).equals(exerciseName)) {
                maxViews.add(v);
            }
        }
        int weight = 0;
        View returnedV = maxViews.get(0);
        for(View v : maxViews) {
            EditText weightEt = (EditText) v.findViewById(R.id.et_weight);
            if(Integer.parseInt(String.valueOf(weightEt.getText())) > weight) {
                weight = Integer.parseInt(String.valueOf(weightEt.getText()));
                returnedV = v;
            }
        }

        return returnedV;
    }
}
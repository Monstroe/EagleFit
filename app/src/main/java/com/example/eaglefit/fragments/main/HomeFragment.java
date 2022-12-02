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
import java.util.Random;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private int dayOfWeekInt;
    private String dayOfWeekStr;

    private PlansQueryHelper plansQueryHelper;
    private UserWorkoutsQueryHelper userWorkoutsQueryHelper;

    private Button startWorkoutBtn;

    private GraphView graphView;
    private String graph_exercise = Random_Exercise();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        graphView = view.findViewById(R.id.idGraphView);

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

        try {
            //Adds datapoints to the graph and sets it up
            BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(0, 1),
                    new DataPoint(1, 3),
                    new DataPoint(2, 4),
                    new DataPoint(3, 9),
                    new DataPoint(4, 6),
                    new DataPoint(5, 3),
                    new DataPoint(6, 6),
                    new DataPoint(7, 1),
                    new DataPoint(8, 2)
            });
            //BarGraphSeries<DataPoint> series[7] = Create_DataPoints(graph_exercise);
            graphView.setTitle("" + graph_exercise + " Progress");
            graphView.setTitleColor(R.color.purple_200);
            graphView.setTitleTextSize(58);
            graphView.addSeries(series);
        }
        catch (Exception e) {
            graphView.setTitle("Error fetching data");
        }

        // Inflate the layout for this fragment
        return view;
    }

    /* public BarGraphSeries<DataPoint> Create_DataPoints(String exercise) {
        private BarGraphSeries<DataPoint> graph_data = new BarGraphSeries<DataPoint>(new DataPoint[7]);
        saved_exercise_date = new exercise data
        private int total_data = saveddata.gettotal???();

        private int graph_counter = 0;
        for (int i = 0; i < 7; i++) {
            if (exercise.equals(exercise_data)) {
                graph_data[graph_counter] = new DataPoint(graph_counter, data.sits*data.reps);
                graph_counter++;
            }
        }


    } */
    public String Random_Exercise(){
        int rand_exercise = new Random().nextInt(3) + 1;
        String exercise = "";

        if (rand_exercise == 1)
            exercise = "Bench Press";
        else if (rand_exercise == 2)
            exercise = "Push-Ups";
        else if (rand_exercise == 3)
            exercise = "Sit-Ups";
        return exercise;
    }
}
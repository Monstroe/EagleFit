package com.example.eaglefit.fragments.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.eaglefit.R;
import com.example.eaglefit.database.UserInfoQueryHelper;
import com.example.eaglefit.database.UserProgressQueryHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

public class PersonalFragment extends Fragment {

    private LineGraphSeries<DataPoint> benchSeries;
    private LineGraphSeries<DataPoint> squatSeries;
    private LineGraphSeries<DataPoint> deadliftSeries;

    private EditText nameEt;
    private EditText ageEt;
    private EditText heightEt;
    private EditText weightEt;

    private UserProgressQueryHelper userProgressQueryHelper;
    private UserInfoQueryHelper userInfoQueryHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);

        userProgressQueryHelper = new UserProgressQueryHelper(getContext());
        userInfoQueryHelper = new UserInfoQueryHelper(getContext());

        nameEt = (EditText) view.findViewById(R.id.et_name);
        ageEt = (EditText) view.findViewById(R.id.et_age);
        heightEt = (EditText) view.findViewById(R.id.et_height);
        weightEt = (EditText) view.findViewById(R.id.et_weight);

        nameEt.setText(userInfoQueryHelper.grabName());
        ageEt.setText(String.valueOf(userInfoQueryHelper.grabAge()));
        heightEt.setText(String.valueOf(userInfoQueryHelper.grabHeight()));
        weightEt.setText(String.valueOf(userInfoQueryHelper.grabWeight()));

        nameEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                userInfoQueryHelper.insertName(String.valueOf(nameEt.getText()));
            }
        });
        ageEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                userInfoQueryHelper.insertAge(Integer.parseInt(String.valueOf(ageEt.getText())));
            }
        });
        heightEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                userInfoQueryHelper.insertHeight(Integer.parseInt(String.valueOf(heightEt.getText())));
            }
        });
        weightEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                userInfoQueryHelper.insertWeight(Integer.parseInt(String.valueOf(weightEt.getText())));
            }
        });

        GraphView graphBench = (GraphView) view.findViewById(R.id.gv_bench);
        benchSeries = new LineGraphSeries<>();
        List<Integer> list1 = userProgressQueryHelper.grabWeightExerciseData("Flat Bench Press");
        for(int i = 0; i < list1.size(); i++) {
            benchSeries.appendData(new DataPoint(i, list1.get(i)), true, list1.size());
        }
        graphBench.addSeries(benchSeries);

        GraphView graphSquat = (GraphView) view.findViewById(R.id.gv_squat);
        squatSeries = new LineGraphSeries<>();
        List<Integer> list2 = userProgressQueryHelper.grabWeightExerciseData("Back Squat");
        for(int i = 0; i < list2.size(); i++) {
            squatSeries.appendData(new DataPoint(i, list2.get(i)), true, list2.size());
        }
        graphSquat.addSeries(squatSeries);

        GraphView graphDeadlift = (GraphView) view.findViewById(R.id.gv_deadlift);
        deadliftSeries = new LineGraphSeries<>();
        List<Integer> list3 = userProgressQueryHelper.grabWeightExerciseData("Barbell Deadlift");
        for(int i = 0; i < list3.size(); i++) {
            deadliftSeries.appendData(new DataPoint(i, list3.get(i)), true, list3.size());
        }
        graphDeadlift.addSeries(deadliftSeries);

        // Inflate the layout for this fragment
        return view;
    }
}
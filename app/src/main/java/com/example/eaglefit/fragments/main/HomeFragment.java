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

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

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

        startWorkoutBtn = (Button) view.findViewById(R.id.btn_start_workout);
        startWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                Intent intent = new Intent(getActivity(), StartWorkoutActivity.class);
                intent.putExtra("Day", dayOfWeek);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}
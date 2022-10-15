package com.example.eaglefit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.eaglefit.fragments.DashboardFragment;
import com.example.eaglefit.fragments.MuscleChartFragment;
import com.example.eaglefit.fragments.StatsFragment;
import com.example.eaglefit.fragments.WorkoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView menuNav = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, new DashboardFragment()).commit();

        menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.dashboard: fragment = new DashboardFragment();
                        break;
                    case R.id.muscle_chart: fragment = new MuscleChartFragment();
                        break;
                    case R.id.workout: fragment = new WorkoutFragment();
                        break;
                    case R.id.stats: fragment = new StatsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();

                return true;
            }
        });

    }
}
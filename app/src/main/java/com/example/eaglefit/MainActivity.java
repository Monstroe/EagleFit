package com.example.eaglefit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.eaglefit.database.DatabaseHelper;
import com.example.eaglefit.fragments.HomeFragment;
import com.example.eaglefit.fragments.MuscleChartFragment;
import com.example.eaglefit.fragments.PersonalFragment;
import com.example.eaglefit.fragments.WorkoutFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dataBase = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up bottom menu
        BottomNavigationView menuNav = findViewById(R.id.bottom_navigation);
        menuNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                setActivePage(item.getItemId());
                return true;
            }
        });
        //Start on dashboard page
        setActivePage(R.id.home);



    }

    public void setActivePage(int pageId) {
        Fragment fragment = null;
        switch (pageId) {
            case R.id.home: fragment = new HomeFragment();
                break;
            case R.id.muscle_chart: fragment = new MuscleChartFragment();
                break;
            case R.id.workout: fragment = new WorkoutFragment();
                break;
            case R.id.personal: fragment = new PersonalFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_wrapper, fragment).commit();
    }

}
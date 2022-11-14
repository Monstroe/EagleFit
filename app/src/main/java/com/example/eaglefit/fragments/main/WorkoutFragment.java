package com.example.eaglefit.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.eaglefit.R;
import com.example.eaglefit.WorkoutPlanActivity;
import com.example.eaglefit.database.PlansQueryHelper;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment {

    private static final String TAG = "WorkoutFragment";

    private PlansQueryHelper plansQueryHelper;
    private ArrayList<String> plans;
    private ArrayAdapter<String> plansAdapter;

    //private ImageButton addPlanBtn;
    private Button createPlanBtn;
    private ListView workoutPlansLv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        plansQueryHelper = new PlansQueryHelper(view.getContext());
        plans = plansQueryHelper.grabWorkoutPlanNames();
        plansAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, plans);

        workoutPlansLv = (ListView) view.findViewById(R.id.gv_plans);
        workoutPlansLv.setAdapter(plansAdapter);

        createPlanBtn = (Button) view.findViewById(R.id.btn_create_plan);
        createPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_add_plan, null);

                // create the popup window
                int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                Button button = (Button) popupView.findViewById(R.id.btn_add);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText editText = (EditText) popupView.findViewById(R.id.et_new);
                        if(String.valueOf(editText.getText()).equals("")) return;
                        else if(plansQueryHelper.doesPlanNameExist(String.valueOf(editText.getText()))) {
                            Toast toast = Toast.makeText(view.getContext(), "Plan name already exists!", Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }

                        String planName = String.valueOf(editText.getText());
                        plansQueryHelper.insertNewWorkoutPlan(planName);
                        plans.add(planName);
                        plansAdapter.notifyDataSetChanged();

                        popupWindow.dismiss();
                    }
                });
            }
        });

        workoutPlansLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItemName = (String) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), WorkoutPlanActivity.class);
                intent.putExtra("Name", selectedItemName);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
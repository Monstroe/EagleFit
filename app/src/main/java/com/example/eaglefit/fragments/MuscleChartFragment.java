package com.example.eaglefit.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

//import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import androidx.appcompat.widget.SwitchCompat;


import com.example.eaglefit.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MuscleChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MuscleChartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Variables for switch, images, buttons, etc.
    private SwitchCompat chartFlipper;
    private ImageView frontIm;
    private ImageView backIm;

    public MuscleChartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MuscleChartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MuscleChartFragment newInstance(String param1, String param2) {
        MuscleChartFragment fragment = new MuscleChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //Switch properties and actions ISSUE IS HERE
//        chartFlipper = (SwitchCompat) chartFlipper.findViewById(R.id.chartFlipper);
//        frontIm = (ImageView) frontIm.findViewById(R.id.imageFront);
//        backIm = (ImageView) backIm.findViewById(R.id.imageBack);
//        Boolean switchState = chartFlipper.isChecked();
//
//        if(switchState){
//            frontIm.setVisibility(View.VISIBLE);
//            backIm.setVisibility(View.INVISIBLE);
//        }else{
//            frontIm.setVisibility((View.INVISIBLE));
//            backIm.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Switch properties and actions ISSUE IS HERE
        return inflater.inflate(R.layout.fragment_muscle_chart, container, false);
    }

}
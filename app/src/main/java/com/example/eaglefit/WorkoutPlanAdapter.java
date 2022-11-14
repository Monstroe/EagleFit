package com.example.eaglefit;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class WorkoutPlanAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> array;

    public WorkoutPlanAdapter(Context ctx, int layout, ArrayList<String> mArray) {
        super(ctx, layout, mArray);
        this.context = ctx;
        this.array = mArray;
        for (int i = 0; i < mArray.size(); i++) // copy global ArrayList
            this.array.add(mArray.get(i));
    }

    public void newDataReceived(ArrayList<String> newArr){
        this.array.clear();
        for (int i = 0; i < newArr.size(); i++)
            this.array.add(newArr.get(i));

        this.notifyDataSetChanged();
    }

}

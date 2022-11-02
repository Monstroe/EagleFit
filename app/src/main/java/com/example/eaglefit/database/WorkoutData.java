package com.example.eaglefit.database;

import android.os.Parcel;
import android.os.Parcelable;

public class WorkoutData implements Parcelable {

    private String exerciseName;
    private String exerciseDescription;

    public WorkoutData(String name, String description) {
        exerciseName = name;
        exerciseDescription = description;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(exerciseName);
        parcel.writeString(exerciseDescription);
    }

    // example constructor that takes a Parcel and gives you an object populated with it's values
    protected WorkoutData(Parcel in) {
        exerciseName = in.readString();
        exerciseDescription = in.readString();
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Creator<WorkoutData> CREATOR = new Creator<WorkoutData>() {
        @Override
        public WorkoutData createFromParcel(Parcel in) {
            return new WorkoutData(in);
        }

        @Override
        public WorkoutData[] newArray(int size) {
            return new WorkoutData[size];
        }
    };
}

package com.example.eaglefit.database;

public class WorkoutData {
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

}

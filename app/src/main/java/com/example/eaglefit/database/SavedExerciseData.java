package com.example.eaglefit.database;

public class SavedExerciseData {

    private String exerciseName;
    private int sets;
    private int reps;

    public SavedExerciseData() {

    }

    public SavedExerciseData(String name) {
        exerciseName = name;
        this.sets = 0;
        this.reps = 0;
    }

    public SavedExerciseData(String name, int sets, int reps) {
        exerciseName = name;
        this.sets = sets;
        this.reps = reps;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}

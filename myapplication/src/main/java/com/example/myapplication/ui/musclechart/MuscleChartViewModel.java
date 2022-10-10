package com.example.myapplication.ui.musclechart;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MuscleChartViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MuscleChartViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is muscle chart fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
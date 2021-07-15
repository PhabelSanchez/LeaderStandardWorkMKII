package com.example.leaderstandardworkmkii.ui.planning;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlanningViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlanningViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}
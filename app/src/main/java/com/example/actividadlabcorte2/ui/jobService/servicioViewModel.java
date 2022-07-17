package com.example.actividadlabcorte2.ui.jobService;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class servicioViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public servicioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
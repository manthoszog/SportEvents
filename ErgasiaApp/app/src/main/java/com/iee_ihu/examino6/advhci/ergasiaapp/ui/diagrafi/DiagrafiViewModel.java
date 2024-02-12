package com.iee_ihu.examino6.advhci.ergasiaapp.ui.diagrafi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiagrafiViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public DiagrafiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
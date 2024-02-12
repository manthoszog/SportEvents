package com.iee_ihu.examino6.advhci.ergasiaapp.ui.eisagogi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Eisagogi2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Eisagogi2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
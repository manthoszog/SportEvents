package com.iee_ihu.examino6.advhci.ergasiaapp.ui.erotimata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ErotimataViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public ErotimataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
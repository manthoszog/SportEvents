package com.iee_ihu.examino6.advhci.ergasiaapp.ui.arxiki;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArxikiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ArxikiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
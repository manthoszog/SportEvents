package com.iee_ihu.examino6.advhci.ergasiaapp.ui.tropopoiisi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Tropopoiisi2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Tropopoiisi2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
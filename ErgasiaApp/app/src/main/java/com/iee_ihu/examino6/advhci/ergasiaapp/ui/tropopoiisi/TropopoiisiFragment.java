package com.iee_ihu.examino6.advhci.ergasiaapp.ui.tropopoiisi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.iee_ihu.examino6.advhci.ergasiaapp.R;

public class TropopoiisiFragment extends Fragment {

    private TropopoiisiViewModel tropopoiisiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tropopoiisiViewModel =
                new ViewModelProvider(this).get(TropopoiisiViewModel.class);
        View root = inflater.inflate(R.layout.tropopoiisi, container, false);
        /*final TextView textView = root.findViewById(R.id.text_slideshow);
        tropopoiisiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
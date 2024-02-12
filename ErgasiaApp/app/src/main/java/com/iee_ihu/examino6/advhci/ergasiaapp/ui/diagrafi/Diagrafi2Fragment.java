package com.iee_ihu.examino6.advhci.ergasiaapp.ui.diagrafi;

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

public class Diagrafi2Fragment extends Fragment {

    private Diagrafi2ViewModel diagrafi2ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diagrafi2ViewModel =
                new ViewModelProvider(this).get(Diagrafi2ViewModel.class);
        View root = inflater.inflate(R.layout.diagrafi2_fragment, container, false);
        /*final TextView textView = root.findViewById(R.id.text_diagrafi2);
        diagrafi2ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

}
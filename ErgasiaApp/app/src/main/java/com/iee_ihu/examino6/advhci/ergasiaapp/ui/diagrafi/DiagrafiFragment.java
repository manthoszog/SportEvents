package com.iee_ihu.examino6.advhci.ergasiaapp.ui.diagrafi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iee_ihu.examino6.advhci.ergasiaapp.R;

public class DiagrafiFragment extends Fragment {

    private DiagrafiViewModel diagrafiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diagrafiViewModel =
                new ViewModelProvider(this).get(DiagrafiViewModel.class);
        View root = inflater.inflate(R.layout.diagrafi_fragment, container, false);
        /*final TextView textView = root.findViewById(R.id.text_diagrafi2);
        diagrafiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

}
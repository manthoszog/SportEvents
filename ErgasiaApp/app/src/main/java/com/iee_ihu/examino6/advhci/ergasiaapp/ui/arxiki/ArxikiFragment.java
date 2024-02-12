package com.iee_ihu.examino6.advhci.ergasiaapp.ui.arxiki;

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

public class ArxikiFragment extends Fragment {

    private ArxikiViewModel arxikiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        arxikiViewModel =
                new ViewModelProvider(this).get(ArxikiViewModel.class);
        View root = inflater.inflate(R.layout.arxiki, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        arxikiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
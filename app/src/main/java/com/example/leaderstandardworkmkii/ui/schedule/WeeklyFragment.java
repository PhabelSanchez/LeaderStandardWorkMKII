package com.example.leaderstandardworkmkii.ui.schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leaderstandardworkmkii.R;

public class WeeklyFragment extends Fragment {
    TextView textView;

    public WeeklyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekly, container, false);

        textView = view.findViewById(R.id.weeklyFragment);
//        Bundle bundle = this.getArguments();
//        if(bundle == null) {
//            Log.d("WeeklyFragment","There is no task stated");
//        } else {
//            String data = bundle.getString("fromPlanning");
//            textView.setText(data);
//        }

        return view;
    }
}
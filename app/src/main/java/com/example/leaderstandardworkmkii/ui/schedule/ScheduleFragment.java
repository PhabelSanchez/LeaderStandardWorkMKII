package com.example.leaderstandardworkmkii.ui.schedule;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leaderstandardworkmkii.R;
import com.example.leaderstandardworkmkii.databinding.FragmentHomeBinding;
import com.example.leaderstandardworkmkii.databinding.FragmentScheduleBinding;
import com.example.leaderstandardworkmkii.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;
    private FragmentScheduleBinding scheduleBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        scheduleBinding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = scheduleBinding.getRoot();

        final ConstraintLayout constraintView = scheduleBinding.frameLayout;
        scheduleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                constraintView.getViewById(R.id.nav_schedule_view);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        scheduleBinding = null;
    }

}
package com.example.leaderstandardworkmkii.ui.schedule;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leaderstandardworkmkii.R;
import com.example.leaderstandardworkmkii.databinding.FragmentHomeBinding;
import com.example.leaderstandardworkmkii.databinding.FragmentScheduleBinding;
import com.example.leaderstandardworkmkii.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;
    private FragmentScheduleBinding scheduleBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        scheduleBinding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = scheduleBinding.getRoot();

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.schedule_container, new DailyFragment()).commit();

        final ConstraintLayout constraintView = scheduleBinding.frameLayout;
        scheduleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                constraintView.getViewById(R.id.nav_schedule_view);
            }
        });
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView scheduleView = (BottomNavigationView) view.findViewById(R.id.nav_schedule_view);
        scheduleView.setOnNavigationItemSelectedListener(mOnNavogationItemSelectedListener);

//        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_schedule_to_dailyFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavogationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_daily:
                    fragmentTransaction.replace(R.id.schedule_container, new DailyFragment()).commit();
                    return true;
                case R.id.navigation_weekly:
                    fragmentTransaction.replace(R.id.schedule_container, new WeeklyFragment()).commit();
                    return true;
                case R.id.navigation_monthly:
                    fragmentTransaction.replace(R.id.schedule_container, new MonthlyFragment()).commit();
                    return true;
            }
            return false;
        }
    };



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        scheduleBinding = null;
    }

}
package com.example.leaderstandardworkmkii.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.leaderstandardworkmkii.R;
import com.example.leaderstandardworkmkii.databinding.FragmentScheduleBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ScheduleFragment extends Fragment {

    private ScheduleViewModel scheduleViewModel;
    private FragmentScheduleBinding binding;
    private BottomNavigationView schNavigation;

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

////        final RecyclerView recyclerView = binding.recyclerView;
////        scheduleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
////            @Override
////            public void onChanged(@Nullable String s) {
////                recyclerView.getChildLayoutPosition(0);
////            }
//        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomNavigationView scheduleView = (BottomNavigationView) view.findViewById(R.id.nav_schedule_view);
        scheduleView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        binding = FragmentScheduleBinding.bind(view);
        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_schedule_to_dailyFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;

    {
        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            private MenuItem item;


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                this.item = item;
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.navigation_daily:
                        fragmentTransaction.replace(R.id.schedule_fragment_container, new DailyFragment()).commit();
                        return true;
                    case R.id.navigation_weekly:
                        fragmentTransaction.replace(R.id.schedule_fragment_container, new WeeklyFragment()).commit();
                        return true;
                    case R.id.navigation_monthly:
                        fragmentTransaction.replace(R.id.schedule_fragment_container, new MonthlyFragment()).commit();
                        return true;
                }
                return false;
            }
        };
    }
//


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
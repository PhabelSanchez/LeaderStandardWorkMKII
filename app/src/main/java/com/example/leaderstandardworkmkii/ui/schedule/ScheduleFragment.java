package com.example.leaderstandardworkmkii.ui.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.leaderstandardworkmkii.MainActivity;
import com.example.leaderstandardworkmkii.R;
import com.example.leaderstandardworkmkii.databinding.FragmentScheduleBinding;
import com.example.leaderstandardworkmkii.ui.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    private ScheduleViewModel scheduleViewModel;
    private FragmentScheduleBinding binding;
    private BottomNavigationView schNavigation;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ScheduleAdapter scheduleAdapter;


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        scheduleViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<Task> tasks = MainActivity.taskArrayList;

        SharedPreferences prefs = getContext().getSharedPreferences("TASKS_SAVED", Context.MODE_PRIVATE);

        root = inflater.inflate(R.layout.fragment_schedule, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(new ScheduleAdapter(getContext(),tasks, R.layout.fragment_daily));

        schNavigation = (BottomNavigationView) getActivity().findViewById(R.id.nav_schedule_view);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        schNavigation = (BottomNavigationView) view.findViewById(R.id.nav_schedule_view);
        schNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        binding = FragmentScheduleBinding.bind(view);
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
                ArrayList<Task> tasks = MainActivity.taskArrayList;

                switch (item.getItemId()) {
                    case R.id.navigation_daily:
                        recyclerView.setAdapter(new ScheduleAdapter(getContext(),tasks, R.layout.fragment_daily));
                        return true;
                    case R.id.navigation_weekly:
                        recyclerView.setAdapter(new ScheduleAdapter(getContext(),tasks, R.layout.fragment_weekly));
                        return true;
                    case R.id.navigation_monthly:
                        recyclerView.setAdapter(new ScheduleAdapter(getContext(),tasks, R.layout.fragment_monthly));
                        return true;
                }
                return false;
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.leaderstandardworkmkii.ui.planning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.leaderstandardworkmkii.MainActivity;
import com.example.leaderstandardworkmkii.R;
import com.example.leaderstandardworkmkii.databinding.FragmentPlanningBinding;
import com.example.leaderstandardworkmkii.ui.Task;
import com.example.leaderstandardworkmkii.ui.schedule.ScheduleFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class PlanningFragment extends Fragment {

    private PlanningViewModel planningViewModel;
    private FragmentPlanningBinding binding;
    private ArrayList<Object> testArrayList = new  ArrayList();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        planningViewModel =
                new ViewModelProvider(this).get(PlanningViewModel.class);

        binding = FragmentPlanningBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(PlanningFragment.getContext(),
                //android.R.layout.simple_list_item_1, testArrayList);

        final EditText editText = binding.taskNameField;
        planningViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                editText.getText();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.captureButton.setOnClickListener(v -> {
            String taskName = binding.taskNameField.getText().toString();
            String taskDescription = binding.descriptionField.getText().toString();
            String taskCategory = binding.categoryField.getText().toString();
            String taskRepetition = binding.repetitionField.getText().toString();
            String concat = taskName + taskDescription + taskCategory + taskRepetition;
            binding.testingText.setText(concat);
            Task task = new Task();
            task.setTask_name(taskName);
            task.setDescription(taskDescription);
            task.setCategory(taskCategory);
            task.setRepetition(taskRepetition);
            MainActivity.taskArrayList.add(task);
            //Object lacosa = testArrayList.get(0);
            //String latext = String.valueOf(lacosa.getTask_name());
            //Toast.makeText(getContext(), latext, Toast.LENGTH_SHORT).show();






//            Bundle bundle = new Bundle(); //Ema
//            bundle.putString("fromPlanning", concat); //Ema
//            ScheduleFragment fragment = new ScheduleFragment(); //Ema
//            fragment.setArguments(bundle); //Ema
//            getParentFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, fragment).commit(); //Ema
    });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
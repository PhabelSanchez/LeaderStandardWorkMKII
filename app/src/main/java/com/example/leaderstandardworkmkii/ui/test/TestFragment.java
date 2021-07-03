package com.example.leaderstandardworkmkii.ui.test;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.leaderstandardworkmkii.MainActivity;
import com.example.leaderstandardworkmkii.databinding.FragmentTestBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TestFragment extends Fragment {

    private TestViewModel mViewModel;
    private FragmentTestBinding binding;
    public ArrayList<Object> toShowList = new ArrayList<>();


    public void getTheList (){
        toShowList.add("hi");
        toShowList.add("this is a test");
        toShowList.add("is this Ok?");
        }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getTheList();
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, toShowList);
        //  binding.testList.setAdapter(arrayAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        // TODO: Use the ViewModel
    }

}
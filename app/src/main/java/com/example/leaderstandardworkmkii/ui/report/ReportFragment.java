package com.example.leaderstandardworkmkii.ui.report;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.leaderstandardworkmkii.R;
import com.example.leaderstandardworkmkii.databinding.FragmentReportBinding;

import java.io.File;
import java.io.FileOutputStream;

public class ReportFragment extends Fragment {

    private ReportViewModel reportViewModel;
    private FragmentReportBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reportViewModel =
                new ViewModelProvider(this).get(ReportViewModel.class);

        binding = FragmentReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        final TextView textView = binding.textNotifications;
        reportViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((Button) view.findViewById(R.id.button)).setOnClickListener(this::export);
    }

    public void export(View view){
        //generate data

        StringBuilder data = new StringBuilder();
        data.append("Time,Distance");
        for(int i = 0; i<5; i++){
            data.append("/n"+ (i) + ","+ (i*i));
        }
        try{
            //saving the file into device
            FileOutputStream out = getContext().openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write((data.toString()).getBytes());
            out.close();

            //exporting
            Context context = getActivity().getApplicationContext();
            File filelocation =  new File(getActivity().getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "com.example.LeaderStandardWorkMKII.fileprovider", filelocation);
            Intent fileintent = new Intent(Intent.ACTION_SEND);
            fileintent.setType("text/csv");
            fileintent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileintent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileintent, "Send mail"));

        }
        catch (Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.leaderstandardworkmkii.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderstandardworkmkii.MainActivity;
import com.example.leaderstandardworkmkii.R;
import com.example.leaderstandardworkmkii.ui.Task;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    private ArrayList<Task> tasks = MainActivity.taskArrayList;
    private LayoutInflater myInflater;
    private View.OnClickListener myClickListener;
    private int recyclerRow;

    ScheduleAdapter(Context context, ArrayList<Task> data, int recyclerRow) {
        this.myInflater = LayoutInflater.from(context);
        this.tasks = data;
        this.recyclerRow = recyclerRow;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = myInflater.inflate(recyclerRow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.tvTitle.setText(task.getTask_name()); //I need the methods of necessary to have this information here
        holder.tvDescription.setText(task.getDescription()); // I would need to call here for example a "currentTask.getTaskName()"
        holder.tvCategory.setText(task.getCategory());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = (ArrayList<Task>) tasks;
        notifyDataSetChanged(); //temporary
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvTitle;
        private TextView tvDescription;
        private TextView tvCategory;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvCategory = itemView.findViewById(R.id.tv_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (myClickListener != null) myClickListener.onClick(v);
        }

        Task getItem(int id) {
            return tasks.get(id);
        }
//
//        void setClickListener(ItemClickListener itemClickListener) {
//            this.myClickListener = itemClickListener;
//        }
//
//        public interface ItemClickListener {
//            void onItemClick(View view, int position);
//        }
    }
}

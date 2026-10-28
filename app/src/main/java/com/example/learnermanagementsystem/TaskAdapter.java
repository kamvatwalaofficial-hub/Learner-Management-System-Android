package com.example.learnermanagementsystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    Context context;
    List<Task> taskList;
    LayoutInflater inflater;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return taskList.get(position).getTaskID();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder")
        View view = inflater.inflate(R.layout.task_list_item, null);

        TextView txtName = view.findViewById(R.id.txtTaskName);
        TextView txtDue = view.findViewById(R.id.txtDueDate);
        TextView txtModule = view.findViewById(R.id.txtModuleId);

        Task task = taskList.get(position);

        txtName.setText("Task: " + task.getTaskName());
        txtDue.setText("Due: " + task.getDueDate());
        txtModule.setText("Module ID: " + task.getModuleID());

        return view;
    }
}

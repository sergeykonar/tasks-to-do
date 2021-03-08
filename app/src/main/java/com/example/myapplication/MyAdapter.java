package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;

    public MyAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    private List<Task> taskList = new ArrayList<>();

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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        Task task = (Task) getItem(position);
        TextView taskName= v.findViewById(R.id.text_view_item_name);
        TextView taskDescription = v.findViewById(R.id.text_view_item_description);

        taskName.setText(task.getTaskName());
        taskDescription.setText(task.getTaskDescription());

        return v;
    }
}

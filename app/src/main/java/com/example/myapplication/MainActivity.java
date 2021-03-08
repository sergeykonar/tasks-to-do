package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public static MyAdapter adapter;
    public static List<Task> tasks;
    private FloatingActionButton fab;
    public static MyDB mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        tasks = new ArrayList<>();


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(fabListener);


        mDataBase = Room.databaseBuilder(getApplicationContext(), MyDB.class, "myDB").allowMainThreadQueries().build();
        TaskDao taskDao = mDataBase.taskDao();
        tasks = taskDao.getAll();

        adapter = new MyAdapter(this, tasks);
        listView.setAdapter(adapter);

    }

    View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment dialogFragment = new InputDialog();
            dialogFragment.show(getSupportFragmentManager(), "dialog");
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        TaskDao taskDao = mDataBase.taskDao();
        tasks.addAll(taskDao.getAll());
        taskDao.deleteAll();
    }
}
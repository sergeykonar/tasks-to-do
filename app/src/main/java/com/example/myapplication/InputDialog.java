package com.example.myapplication;

import android.app.Dialog;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;


public class InputDialog extends DialogFragment {
    private Button btnOK;
    private static TextInputEditText taskToDo;
    private static TextInputEditText taskDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_custom_dialog, container, false);

        btnOK =v.findViewById(R.id.okBtn);
        taskToDo = v.findViewById(R.id.taskToDo);
        taskDescription = v.findViewById(R.id.taskDescription);
        btnOK.setOnClickListener(okListener);

        return v;
    }

    View.OnClickListener okListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Task newTask = new Task(taskToDo.getText().toString(), taskDescription.getText().toString());
            TaskDao taskDao = MainActivity.mDataBase.taskDao();
            taskDao.insertTask(newTask);

            MainActivity.tasks.add(taskDao.getLast());

            MainActivity.adapter.notifyDataSetChanged();
            dismiss();

        }
    };
}

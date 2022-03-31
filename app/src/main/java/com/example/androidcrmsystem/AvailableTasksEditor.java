package com.example.androidcrmsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AvailableTasksEditor extends AppCompatActivity {

    RecyclerView AvailableTasksRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_tasks_editor);
    }
}
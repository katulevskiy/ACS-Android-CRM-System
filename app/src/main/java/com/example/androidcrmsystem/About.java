package com.example.androidcrmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class About extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewById(R.id.buttonSubmitTask).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(About.this, MainActivity.class);
        startActivity(intent);
    }
}
package com.example.androidcrmsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class completeTask extends AppCompatActivity implements View.OnClickListener {

    private EditText ETtaskId;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_task);

        findViewById(R.id.buttonTaskIdComplete).setOnClickListener(this);
        ETtaskId = (EditText)findViewById(R.id.editTextTaskIdToComplete);
    }

    @Override
    public void onClick(View v) {

        mAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        if (v.getId() == R.id.buttonTaskIdComplete){
            System.out.println(ETtaskId.getText().toString());
            if (ETtaskId.getText().toString().equals("0")){
                myRef.child(uid).child("Tasks").child(ETtaskId.getText().toString()).child("Title").setValue("No active tasks");
                myRef.child(uid).child("Tasks").child(ETtaskId.getText().toString()).child("Description").setValue(null);
                myRef.child(uid).child("Tasks").child(ETtaskId.getText().toString()).child("Deadline").setValue(null);
            }

            else{myRef.child(uid).child("Tasks").child(ETtaskId.getText().toString()).setValue(null);}
            Intent intent = new Intent(completeTask.this, ListTasks.class);
            startActivity(intent);
        }

    }
}
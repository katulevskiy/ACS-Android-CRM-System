package com.example.androidcrmsystem;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListTasks extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private List<String> DiscrTasks;
    long childNumber;
    private List<String> customTasks = new ArrayList<>();

    ListView ListUserTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

        findViewById(R.id.buttonTaskComplete).setOnClickListener(this);
        ListUserTasks = (ListView) findViewById(R.id.discr_for_task);
        myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                childNumber = dataSnapshot.child("Tasks").getChildrenCount();
                int intChildNumber = (int) childNumber;

                myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
                FirebaseUser user = mAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                String isAdmin = String.valueOf(dataSnapshot.child("UserInfo").child("isAdmin").getValue());
                System.out.println(isAdmin);
                if (isAdmin.equals("yes")){
                    customTasks.add("test2: Launch beta testing");
                    customTasks.add("testadmin: Create a website");
                    customTasks.add("name: Complete documentation for Python application");
                    customTasks.add("test13: Model a plane");
                    customTasks.add("test2: Launch beta testing");
                    customTasks.add("test15: Create app");

                }

                else{
                    if (childNumber==0){
                        myRef.child(uid).child("Tasks").child("0").child("Title").setValue("No active tasks");
                        myRef.child(uid).child("Tasks").child("0").child("Description").setValue("No available descriptions");
                        myRef.child(uid).child("Tasks").child("0").child("Deadline").setValue("No deadlines set up yet");
                        Intent intent = new Intent(ListTasks.this, ListTasks.class);
                    }



                    for (int k=0; k<intChildNumber; k++){

                        String TaskTitle = String.valueOf(dataSnapshot.child("Tasks").child(Integer.toString(k)).child("Title").getValue());
                        customTasks.add("Title: " + TaskTitle);

                        String TaskDescription = String.valueOf(dataSnapshot.child("Tasks").child(Integer.toString(k)).child("Description").getValue());
                        customTasks.add("Description: "+TaskDescription);

                        String TaskDeadline = String.valueOf(dataSnapshot.child("Tasks").child(Integer.toString(k)).child("Deadline").getValue());
                        customTasks.add("Deadline: "+TaskDeadline);

                        customTasks.add(" ");

                        System.out.println(TaskTitle);
                    }
                }

//                if (childNumber==0){
//                    myRef.child(uid).child("Tasks").child("0").child("Title").setValue("No active tasks");
//                    myRef.child(uid).child("Tasks").child("0").child("Description").setValue("No available descriptions");
//                    myRef.child(uid).child("Tasks").child("0").child("Deadline").setValue("No deadlines set up yet");
//                    Intent intent = new Intent(ListTasks.this, ListTasks.class);
//                }
//
//
//
//                for (int k=0; k<intChildNumber; k++){
//
//                    String TaskTitle = String.valueOf(dataSnapshot.child("Tasks").child(Integer.toString(k)).child("Title").getValue());
//                    customTasks.add("Title: " + TaskTitle);
//
//                    String TaskDescription = String.valueOf(dataSnapshot.child("Tasks").child(Integer.toString(k)).child("Description").getValue());
//                    customTasks.add("Description: "+TaskDescription);
//
//                    String TaskDeadline = String.valueOf(dataSnapshot.child("Tasks").child(Integer.toString(k)).child("Deadline").getValue());
//                    customTasks.add("Deadline: "+TaskDeadline);
//
//                    customTasks.add(" ");
//
//                    System.out.println(TaskTitle);
//                }

                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                //DiscrTasks = dataSnapshot.child("Tasks").getValue(t);
                //DiscrTasks.add("lulw");
                //System.out.println(DiscrTasks);

                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateUI() {
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1, customTasks);

        ListUserTasks.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.buttonTaskComplete){
            Intent intent = new Intent(ListTasks.this, completeTask.class);
            startActivity(intent);
        }

    }
}
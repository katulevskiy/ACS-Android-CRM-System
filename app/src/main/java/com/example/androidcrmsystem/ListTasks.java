package com.example.androidcrmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import java.util.List;

public class ListTasks extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private List<String> DiscrTasks;

    ListView ListUserTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

        ListUserTasks = (ListView) findViewById(R.id.discr_for_task);
        myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        myRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                DiscrTasks = dataSnapshot.child("Tasks").getValue(t);

                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateUI() {
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1, DiscrTasks);

        ListUserTasks.setAdapter(adapter);
    }

}
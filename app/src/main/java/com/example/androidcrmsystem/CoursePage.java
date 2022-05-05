package com.example.androidcrmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CoursePage extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);

        findViewById(R.id.buttonTakeTheTask).setOnClickListener(this);
        ConstraintLayout courseBg = findViewById(R.id.coursePageBg);
        ImageView courseImage = findViewById(R.id.coursePageImage);
        TextView courseTitle = findViewById(R.id.coursePageTitle);
        TextView courseDate = findViewById(R.id.coursePageDate);
        TextView courseLevel = findViewById(R.id.coursePageLevel);
        TextView courseText = findViewById(R.id.coursePageText);

        courseBg.setBackgroundColor(getIntent().getIntExtra("courseBg", 0));
        courseImage.setImageResource(getIntent().getIntExtra("courseImage", 0));
        courseTitle.setText(getIntent().getStringExtra("courseTitle"));
        courseDate.setText(getIntent().getStringExtra("courseDate"));
        courseLevel.setText(getIntent().getStringExtra("courseLevel"));
        courseText.setText(getIntent().getStringExtra("courseText"));


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonTakeTheTask){

            myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
            FirebaseUser user = mAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            myRef.child("Available").child("0").child("Title").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        String title = String.valueOf(task.getResult().getValue());
                        myRef.child(uid).child("Tasks").child("0").child("Title").setValue(title);
                    }
                }
            });

            myRef.child("Available").child("0").child("Description").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        String description = String.valueOf(task.getResult().getValue());
                        myRef.child(uid).child("Tasks").child("0").child("Description").setValue(description);
                    }
                }
            });

            myRef.child("Available").child("0").child("Deadline").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        String deadline = String.valueOf(task.getResult().getValue());
                        myRef.child(uid).child("Tasks").child("0").child("Deadline").setValue(deadline);
                    }
                }
            });

//            String title = String.valueOf(myRef.child("Available").child("0").child("Title").get());
//            String description = String.valueOf(myRef.child("Available").child("0").child("Description").get());
//            String deadline = String.valueOf(myRef.child("Available").child("0").child("Deadline").get());
//            myRef.child(uid).child("Tasks").child("0").child("Title").setValue(title);
//            myRef.child(uid).child("Tasks").child("0").child("Description").setValue(description);
//            myRef.child(uid).child("Tasks").child("0").child("Deadline").setValue(deadline);
            Intent intent = new Intent(CoursePage.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
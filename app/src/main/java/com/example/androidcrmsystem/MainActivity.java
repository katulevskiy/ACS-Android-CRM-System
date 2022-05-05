package com.example.androidcrmsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidcrmsystem.adapter.CategoryAdapter;
import com.example.androidcrmsystem.adapter.CourseAdapter;
import com.example.androidcrmsystem.model.Category;
import com.example.androidcrmsystem.model.Courses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    CourseAdapter courseAdapter;

    private FirebaseAuth mAuth;
    private List<String> DiscrTasks;
    private long tasksNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Category> categoryList = new ArrayList<>();//data file from DB
        categoryList.add(new Category(1, "Tasks"));

        setCategoryRecycler(categoryList);

        List<Courses> coursesList = new ArrayList<>();
        myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

//        myRef.child("Available").addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                tasksNumber = dataSnapshot.child("Available").getChildrenCount();
//                int intTasksNumber = (int) tasksNumber;
//
//                myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
//                FirebaseUser user = mAuth.getInstance().getCurrentUser();
//                String uid = user.getUid();
//
//                for (int k=0; k<intTasksNumber; k++){
//
//                    String img = String.valueOf(dataSnapshot.child("Available").child(Integer.toString(k)).child("Image").getValue());
//                    String title = String.valueOf(dataSnapshot.child("Available").child(Integer.toString(k)).child("Title").getValue());
//                    String date = String.valueOf(dataSnapshot.child("Available").child(Integer.toString(k)).child("Deadline").getValue());
//                    String level = String.valueOf(dataSnapshot.child("Available").child(Integer.toString(k)).child("Id").getValue());
//                    String color = String.valueOf(dataSnapshot.child("Available").child(Integer.toString(k)).child("BG").getValue());
//                    String description = String.valueOf(dataSnapshot.child("Available").child(Integer.toString(k)).child("Description").getValue());
//
//                    coursesList.add(new Courses(k+1, img, title, date, level, color, description));
//                }
//
//                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
//                //DiscrTasks = dataSnapshot.child("Tasks").getValue(t);
//                //DiscrTasks.add("lulw");
//                //System.out.println(DiscrTasks);
//
//                //Intent intent = new Intent(MainActivity.this,MainActivity.class);
//                //startActivity(intent);
//            }
//
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });




        coursesList.add(new Courses(1, "java2", "Create app\nusing Java", "25 march", "beginner", "#424345", "Sample text"));
        coursesList.add(new Courses(2, "python","ML model\non Python", "10 january", "default", "#9FA52D", "test"));

        setCourseRecycler(coursesList);

        findViewById(R.id.buttonMain).setOnClickListener(this);
        findViewById(R.id.buttonTasks).setOnClickListener(this);
        findViewById(R.id.buttonAbout).setOnClickListener(this);
    }



    private void setCourseRecycler(List<Courses> coursesList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this, coursesList);

        courseRecycler.setAdapter(courseAdapter);

    }

    private void setCategoryRecycler(List<Category> categoryList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this, categoryList);

        categoryRecycler.setAdapter(categoryAdapter);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonTasks)
        {
            Intent intent = new Intent(MainActivity.this, ListTasks.class);
            startActivity(intent);
        }

        if(view.getId() == R.id.buttonMain){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        if(view.getId()==R.id.buttonAbout){
            myRef = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app").getReference();
            FirebaseUser user = mAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            myRef.child(uid).child("UserInfo").child("isAdmin").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        String isAdmin = String.valueOf(task.getResult().getValue());
                        if (isAdmin.equals("yes")){
                            Intent intent = new Intent(MainActivity.this, About.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            });
//            Intent intent = new Intent(MainActivity.this, About.class);
//            startActivity(intent);
        }
    }
}
package com.example.androidcrmsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidcrmsystem.adapter.CategoryAdapter;
import com.example.androidcrmsystem.adapter.CourseAdapter;
import com.example.androidcrmsystem.model.Category;
import com.example.androidcrmsystem.model.Courses;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://ultimate-crm-1337-default-rtdb.europe-west1.firebasedatabase.app/");
    DatabaseReference myRef = database.getReference();
    CourseAdapter courseAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<Category> categoryList = new ArrayList<>();//data file from DB
        categoryList.add(new Category(1, "Тикеты"));
        categoryList.add(new Category(2, "Сайты"));
        categoryList.add(new Category(3, "Языки"));
        categoryList.add(new Category(4, "Прочее"));
        categoryList.add(new Category(5, "2005"));

        setCategoryRecycler(categoryList);

        List<Courses> coursesList = new ArrayList<>();
        coursesList.add(new Courses(1, "java2", "Создание приложения\nна Java", "25 марта", "начальный", "#424345", "Здесь будет располагаться какой душе угодно текст с описанием проекта, находящегося в разработке. \n\nФорматирование текста работает, а добавить описание можно в MainActivity.java \n\nТеперь надо реализовать добавление в риал тайм"));
        coursesList.add(new Courses(2, "python","Обучение модели\nна Python", "10 января", "отладка", "#9FA52D", "test"));

        setCourseRecycler(coursesList);
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
}
package com.example.androidcrmsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidcrmsystem.adapter.CategoryAdapter;
import com.example.androidcrmsystem.adapter.CourseAdapter;
import com.example.androidcrmsystem.model.Category;
import com.example.androidcrmsystem.model.Courses;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Тикеты"));
        categoryList.add(new Category(2, "Сайты"));
        categoryList.add(new Category(3, "Языки"));
        categoryList.add(new Category(4, "Прочее"));
        categoryList.add(new Category(5, "2005"));

        setCategoryRecycler(categoryList);

        List<Courses> coursesList = new ArrayList<>();
        coursesList.add(new Courses(1, "java2", "Создание приложения\nна Java", "25 марта", "начальный", "#424345", "Программа обучения Джава – рассчитана на новичков в данной сфере. \n\nЗа программу вы изучите построение графических приложений под ПК, разработку веб сайтов на основе Java Spring Boot, изучите построение полноценных Андроид приложений и отлично изучите сам язык Джава!"));
        coursesList.add(new Courses(2, "python","Обучение модели\nна Python", "10 января", "продвинутый", "#9FA52D", "test"));

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
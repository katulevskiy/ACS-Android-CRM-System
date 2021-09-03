package com.example.androidcrmsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcrmsystem.R;
import com.example.androidcrmsystem.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

///    есть 2 параметра
///    в первый идет контекст страницы на которой юзер
///    во второй - список всех категорий
    Context context;
    List<Category> categories;

///    конструктор для установки всех параметров
    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override

///    указываем дизайн и элементы, с которыми работаем
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override

    ///   Создаем объект на основе вложенного в него класса
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
    ///   Через объект обращаемся к нужным полям - тексту названий категорий,
        // И устанавливаем текст для этих категорий из общего списка, созданного в Category.java
        holder.categoryTitle.setText(categories.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

/// Вложенный класс для onCreateViewHolder для работы с текстовым полем
    public static final class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
///           Устанавливаем ссылку на нужный объект из дизайна
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }

    }

}

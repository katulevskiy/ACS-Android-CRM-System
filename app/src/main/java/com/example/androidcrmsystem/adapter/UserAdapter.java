package com.example.androidcrmsystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidcrmsystem.model.UserInfo;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context context;
    List<UserInfo> users;



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //which template to use
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {  // what data to substitute into the design

    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    public static final class UserViewHolder extends RecyclerView.ViewHolder { // which elements do I interact with

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}

package com.example.androidcrmsystem.model;

public class UserInfo {

    String uid, email, numberOfTasks;

    public UserInfo(String uid, String email, String numberOfTasks) {
        this.uid = uid;
        this.email = email;
        this.numberOfTasks = numberOfTasks;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberOfTasks() {
        return numberOfTasks;
    }

    public void setNumberOfTasks(String numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }



}

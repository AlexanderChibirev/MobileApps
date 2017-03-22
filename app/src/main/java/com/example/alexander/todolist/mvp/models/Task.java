package com.example.alexander.todolist.mvp.models;


import android.support.annotation.NonNull;

import java.util.Date;

import io.realm.RealmObject;

public class Task extends RealmObject {

    @NonNull
    private String mTitle = "";

    @NonNull
    private String mDescription = "";

    @NonNull
    private Date mTaskCompletionDate = new Date();

    @NonNull
    private Boolean mIsCompleted = false;

    private int mPriority;

    public int getPriority() {
        return mPriority;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    @NonNull
    public Boolean getIsCompleted() {
        return mIsCompleted;
    }

    public void setIsCompleted(@NonNull Boolean isCompleted) {
        mIsCompleted = isCompleted;
    }


    @NonNull
    public Date getTaskCompletionDate() {
        return mTaskCompletionDate;
    }

    public void setTaskCompletionDate(@NonNull Date taskCompletionDate) {
        mTaskCompletionDate = taskCompletionDate;
    }


    @NonNull
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(@NonNull String description) {
        mDescription = description;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(@NonNull String title) {
        mTitle = title;
    }

}

package com.example.alexander.todolist.mvp.models;


import android.support.annotation.NonNull;

import io.realm.RealmObject;

public class Task extends RealmObject {

    @NonNull
    private String mTitle = "";

    @NonNull
    private String mDescription = "";

    @NonNull
    private String mTaskCompletionDate = "";

    @NonNull
    private Boolean mIsCompleted = false;


    private int mPos;
    private int mPriority = 0;

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
    public String getTaskCompletionDate() {
        return mTaskCompletionDate;
    }

    public void setTaskCompletionDate(@NonNull String taskCompletionDate) {
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

    public int getPos() {
        return mPos;
    }

    public void setPos(int pos) {
        mPos = pos;
    }
}

package com.example.alexander.todolist.ui;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.todolist.R;
import com.example.alexander.todolist.mvp.presenters.PreviewTaskPresenter;
import com.example.alexander.todolist.mvp.views.PreviewTaskView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.add_task_acrtivity)
public class PreviewTaskActivity extends MvpAppCompatActivity implements PreviewTaskView {

    @InjectPresenter
    PreviewTaskPresenter mPreviewTaskPresenter;

    @Extra("itemPos")
    int mItemPos;

    @ViewById(R.id.editTextDate)
    EditText mDate;

    @ViewById(R.id.editTextDescription)
    EditText mDescription;

    @ViewById(R.id.editTextTitle)
    EditText mTitle;

    @ViewById(R.id.spinnerPriority)
    Spinner mPriority;

    @ViewById(R.id.buttonSaveTask)
    Button mButtonSaveTask;


    @Override
    public void showSelectedItem(String title, String description,
                                 int priority, String taskCompletionDate) {
        mTitle.setText(title);
        mDescription.setText(description);
        mDate.setText(taskCompletionDate);
        initSpinnerPriority(priority);
    }

    private void initSpinnerPriority(int priority) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPriority.setAdapter(adapter);
        mPriority.setSelection(priority);
    }

    @AfterViews
    void init() {
        initButtonSaveTask();
        mPreviewTaskPresenter.initDataSelectedItem(getBaseContext(), mItemPos);
    }

    @Override
    public void showHomeActivity() {
        this.finish();
    }

    void initButtonSaveTask() {
        mButtonSaveTask.setText("Изменить");
        mButtonSaveTask.setOnClickListener((button) -> mPreviewTaskPresenter.onClickButtonSaveTask(
                mDate.getText().toString(),
                mDescription.getText().toString(),
                mTitle.getText().toString(),
                mPriority.getSelectedItemPosition()));
    }
}

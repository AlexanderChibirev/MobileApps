package com.example.alexander.todolist.ui;


import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.alexander.todolist.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.add_task_acrtivity)
public class AddTaskActivity extends MvpAppCompatActivity {

    @ViewById(R.id.spinnerPriority)
    Spinner spinnerPriority;

    @AfterViews
    void init() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriority.setAdapter(adapter);
    }
}

package com.example.alexander.todolist.ui;


import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.todolist.R;
import com.example.alexander.todolist.mvp.presenters.AddTaskPresenter;
import com.example.alexander.todolist.mvp.views.AddTaskView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.add_task_acrtivity)
public class AddTaskActivity extends MvpAppCompatActivity implements AddTaskView {

    @InjectPresenter
    AddTaskPresenter addTaskPresenter;

    @ViewById(R.id.editTextDate)
    EditText date;

    @ViewById(R.id.editTextDescription)
    EditText description;

    @ViewById(R.id.editTextTitle)
    EditText title;

    @ViewById(R.id.spinnerPriority)
    Spinner priority;

    @ViewById(R.id.buttonSaveTask)
    Button buttonSaveTask;


    @AfterViews
    void init() {
        initSpinnerPriority();
        initButtonListener();
    }

    private void initButtonListener() {
        buttonSaveTask.setOnClickListener(
                (button) -> addTaskPresenter.onClickButtonSaveTask(
                        date.getText().toString(),
                        description.getText().toString(),
                        title.getText().toString(),
                        priority.getSelectedItemPosition()));
    }

    private void initSpinnerPriority() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority.setAdapter(adapter);
    }

    @Override
    public void showHomeActivity() {
        closeAddTaskActivity();
        HomeActivity_.intent(this).start();
    }

    private void closeAddTaskActivity() {
        this.finish();
    }
}

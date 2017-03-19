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
    AddTaskPresenter mAddTaskPresenter;

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


    @AfterViews
    void init() {
        initSpinnerPriority();
        initButtonListener();
    }

    @Override
    public void closeAddTaskActivity() {
        this.finish();
    }

    private void initButtonListener() {
        mButtonSaveTask.setOnClickListener(
                (button) -> mAddTaskPresenter.onClickButtonSaveTask(
                        mDate.getText().toString(),
                        mDescription.getText().toString(),
                        mTitle.getText().toString(),
                        mPriority.getSelectedItemPosition()));
    }

    private void initSpinnerPriority() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPriority.setAdapter(adapter);
    }
}

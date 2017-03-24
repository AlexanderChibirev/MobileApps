package com.example.alexander.todolist.ui;


import android.app.DatePickerDialog;
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
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;


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

    @Click(R.id.editTextDate)
    void onEditTextDateClick() {
        showDatePicker();
    }

    @Override
    public void closeAddTaskActivity() {
        this.finish();
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) ->
                        mDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year),
                mYear, mMonth, mDay);
        datePickerDialog.show();
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

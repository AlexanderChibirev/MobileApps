package com.example.alexander.todolist.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.mvp.models.Task;
import com.example.alexander.todolist.mvp.views.AddTaskView;
import com.example.alexander.todolist.utils.DateUtils;

import java.text.ParseException;

import io.realm.Realm;


@InjectViewState
public class AddTaskPresenter extends MvpPresenter<AddTaskView> {

    private Realm mRealm = Realm.getDefaultInstance();

    public void onClickButtonSaveTask(
            String date, String description,
            String title, int idSelectedItemPosition) {

        final Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(idSelectedItemPosition);
        try {
            task.setTaskCompletionDate(DateUtils.stringToDate(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        task.setIsCompleted(false);

        mRealm.executeTransaction(realm -> realm.copyToRealm(task));
        getViewState().closeAddTaskActivity();
    }
}

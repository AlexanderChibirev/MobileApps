package com.example.alexander.todolist.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.mvp.models.Task;
import com.example.alexander.todolist.mvp.views.AddTaskView;

import io.realm.Realm;


@InjectViewState
public class AddTaskPresenter extends MvpPresenter<AddTaskView> {

    public void onClickButtonSaveTask(
            String date, String description,
            String title, int idSelectedItemPosition) {

        Realm mRealm = Realm.getDefaultInstance();

        final Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(idSelectedItemPosition);
        task.setTaskCompletionDate(date);
        task.setIsCompleted(false);
        task.setPos(mRealm.where(Task.class).findAll().size());

        mRealm.executeTransaction(realm -> realm.copyToRealm(task));
        getViewState().closeAddTaskActivity();
    }
}

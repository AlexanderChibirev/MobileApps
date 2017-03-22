package com.example.alexander.todolist.mvp.presenters;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.mvp.models.Task;
import com.example.alexander.todolist.mvp.views.PreviewTaskView;

import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class PreviewTaskPresenter extends MvpPresenter<PreviewTaskView> {
    private Realm mRealm = Realm.getDefaultInstance();
    private Task mTaskSelected;

    public void onClickButtonSaveTask(
            String date, String description,
            String title, int selectedItemPosition) {

        mRealm.executeTransaction(realm -> {
            mTaskSelected.setDescription(description);
            mTaskSelected.setTaskCompletionDate(date);
            mTaskSelected.setTitle(title);
            mTaskSelected.setPriority(selectedItemPosition);
        });

        getViewState().showHomeActivity();
    }

    public void initDataSelectedItem(Context baseContext, int itemPos) {
        Realm.init(baseContext);
        RealmResults<Task> tasks = mRealm.where(Task.class).findAll();
        mTaskSelected = tasks.get(itemPos);
        getViewState().showSelectedItem(
                mTaskSelected.getTitle(),
                mTaskSelected.getDescription(),
                mTaskSelected.getPriority(),
                mTaskSelected.getTaskCompletionDate());
    }
}

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
    private Realm mRealm;

    public void onClickButtonSaveTask() {

    }

    public void initDataSelectedItem(Context baseContext, int itemPos) {
        Realm.init(baseContext);
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmResults<Task> tasks = mRealm.where(Task.class).findAll();
        mRealm.commitTransaction();
        Task taskSelected = tasks.get(itemPos);
        getViewState().showSelectedItem(
                taskSelected.getTitle(),
                taskSelected.getDescription(),
                taskSelected.getPriority(),
                taskSelected.getTaskCompletionDate());
    }
}

package com.example.alexander.todolist.mvp.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.adapters.TaskRVAdepter;
import com.example.alexander.todolist.mvp.models.Task;
import com.example.alexander.todolist.mvp.views.HomeView;

import io.realm.Realm;
import io.realm.RealmResults;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {
    private Realm mRealm;

    public void onClickFab() {
        getViewState().showAddTaskActivity();
    }

    public void loadDataModels(Context baseContext) {
        TaskRVAdepter adapter = new TaskRVAdepter(initTasksFromDB(baseContext));
        getViewState().initRecyclerView(adapter);
    }

    private RealmResults<Task> initTasksFromDB(Context baseContext) {
        Realm.init(baseContext);
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        //mRealm.deleteAll();//TODO::убрать после релиза и добавить очистку кеша в setting
        RealmResults<Task> tasks = mRealm.where(Task.class).findAll();
        mRealm.commitTransaction();
        return tasks;
    }

    public void closeDB() {
        mRealm.close();
    }
}

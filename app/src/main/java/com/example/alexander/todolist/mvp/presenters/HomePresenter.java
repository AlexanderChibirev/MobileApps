package com.example.alexander.todolist.mvp.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.adapters.TaskRVAdepter;
import com.example.alexander.todolist.mvp.models.Task;
import com.example.alexander.todolist.mvp.views.HomeView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

    public void closeDB() {
        mRealm.close();
    }

    public void onClickTask(int itemPos) {
        getViewState().showPreviewActivity(itemPos);
    }

    public void onLongClickTask(int pos, Context context) {
        final RealmResults<Task> tasks = getSortedTasks();
        mRealm.executeTransaction(realm -> {
            tasks.deleteFromRealm(pos);
        });
        getViewState().updateRV();
    }

    public void onClickCheckBox(int pos) {
        final RealmResults<Task> tasks = getSortedTasks();
        mRealm.executeTransaction(realm -> {
            Task task = tasks.get(pos);
            task.setIsCompleted(!task.getIsCompleted());
        });
    }

    private RealmResults<Task> getSortedTasks() {
        String nameFields[] = {"mIsCompleted", "mPriority", "mTaskCompletionDate"};
        Sort typeSorting[] = {Sort.ASCENDING, Sort.DESCENDING, Sort.DESCENDING};
        return mRealm.where(Task.class).findAll().sort(nameFields, typeSorting);
    }

    private RealmResults<Task> initTasksFromDB(Context baseContext) {
        Realm.init(baseContext);
        mRealm = Realm.getDefaultInstance();
        return getSortedTasks();
    }


    public void notifyDB() {
        getSortedTasks();
        getViewState().updateRV();
    }
}

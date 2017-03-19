package com.example.alexander.todolist.mvp.presenters;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.R;
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

    public void closeDB() {
        mRealm.close();
    }

    public void onClickTask(int itemPos) {
        getViewState().showPreviewActivity(itemPos);
    }

    public void onLongClickTask(int pos, Context context) {
        final RealmResults<Task> users = getTasks();
        mRealm.executeTransaction(realm -> {
            users.deleteFromRealm(pos); // Delete and remove object directly
        });

       //mRealm.beginTransaction(); TODO::возможно придется добавить после добавления сортировки
       // removeModel(pos);
      //  refreshModelsPosition(pos);
       // mRealm.commitTransaction();
        getViewState().hideDeletedData(pos);
        getViewState().showMessage(context.getString(R.string.delete_task) + " №: " + (pos + 1));
    }

    private void removeModel(int pos) {
        final RealmResults<Task> result = mRealm.where(Task.class)
                .equalTo("mPos", pos) //mPos field name model
                .findAll();
        result.deleteAllFromRealm();
    }

    private void refreshModelsPosition(int pos) {
        RealmResults<Task> tasks = getTasks();
        for (int i = pos; i < tasks.size(); i++) {
            tasks.get(i).setPos(i - 1);
        }
    }

    private RealmResults<Task> getTasks() {
        return mRealm.where(Task.class).findAll();
    }

    private RealmResults<Task> initTasksFromDB(Context baseContext) {
        Realm.init(baseContext);
        mRealm = Realm.getDefaultInstance();
        return getTasks();
    }
}

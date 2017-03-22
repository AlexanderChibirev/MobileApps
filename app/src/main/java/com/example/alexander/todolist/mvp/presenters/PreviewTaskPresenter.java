package com.example.alexander.todolist.mvp.presenters;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.mvp.models.Task;
import com.example.alexander.todolist.mvp.views.PreviewTaskView;
import com.example.alexander.todolist.utils.DataBaseUtils;
import com.example.alexander.todolist.utils.DateUtils;

import java.text.ParseException;

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
            try {
                mTaskSelected.setTaskCompletionDate(DateUtils.stringToDate(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mTaskSelected.setTitle(title);
            mTaskSelected.setPriority(selectedItemPosition);
        });

        getViewState().showHomeActivity();
    }

    public void initDataSelectedItem(Context baseContext, int itemPos) {
        RealmResults<Task> tasks = DataBaseUtils.getSortedTasks(mRealm);
        mTaskSelected = tasks.get(itemPos);
        getViewState().showSelectedItem(
                mTaskSelected.getTitle(),
                mTaskSelected.getDescription(),
                mTaskSelected.getPriority(),
                DateUtils.dateToString(mTaskSelected.getTaskCompletionDate(), baseContext));
    }
}

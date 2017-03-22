package com.example.alexander.todolist.utils;

import com.example.alexander.todolist.mvp.models.Task;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DataBaseUtils {

    public static RealmResults<Task> getSortedTasks(Realm realm) {
        String nameFields[] = {"mIsCompleted", "mPriority", "mTaskCompletionDate"};
        Sort typeSorting[] = {Sort.ASCENDING, Sort.DESCENDING, Sort.DESCENDING};
        return realm.where(Task.class).findAll().sort(nameFields, typeSorting);
    }
}

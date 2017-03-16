package com.example.alexander.todolist.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.alexander.todolist.mvp.views.HomeView;

@InjectViewState
public class HomePresenter extends MvpPresenter<HomeView> {

    public void onClickFab() {
        getViewState().showCreateTaskActivity();
    }
}

package com.example.alexander.todolist.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.todolist.adapters.TaskRVAdepter;


public interface HomeView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAddTaskActivity();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showPreviewActivity(int itemPos);

    void initRecyclerView(TaskRVAdepter adapter);

    void updateRV();
}

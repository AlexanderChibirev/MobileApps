package com.example.alexander.todolist.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.alexander.todolist.adapters.TaskRVAdepter;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface HomeView extends MvpView {

    void showMessage(String message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAddTaskActivity();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void initRecyclerView(TaskRVAdepter adapter);
}

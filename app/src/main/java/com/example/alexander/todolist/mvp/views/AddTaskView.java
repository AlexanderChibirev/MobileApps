package com.example.alexander.todolist.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface AddTaskView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void closeAddTaskActivity();
}

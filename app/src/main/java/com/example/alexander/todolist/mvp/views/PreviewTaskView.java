package com.example.alexander.todolist.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface PreviewTaskView extends MvpView {

    void showSelectedItem(String title,
                          String description,
                          int priority,
                          String taskCompletionDate);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showHomeActivity();
}

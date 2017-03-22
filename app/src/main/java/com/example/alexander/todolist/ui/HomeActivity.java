package com.example.alexander.todolist.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.todolist.R;
import com.example.alexander.todolist.adapters.OnItemClickListener;
import com.example.alexander.todolist.adapters.TaskRVAdepter;
import com.example.alexander.todolist.mvp.presenters.HomePresenter;
import com.example.alexander.todolist.mvp.views.HomeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter
    HomePresenter mHomePresenter;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.rv)
    RecyclerView mRecyclerView;


    @AfterViews
    void init() {
        initToolbar();
        mHomePresenter.loadDataModels(getBaseContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return (id == R.id.action_settings) || super.onOptionsItemSelected(item);
    }

    @Override
    public void showAddTaskActivity() {
        AddTaskActivity_.intent(this).start();
    }

    @Override
    public void updateRV() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHomePresenter.notifyDB();
    }

    @Override
    public void showPreviewActivity(int itemPos) {
        PreviewTaskActivity_.intent(this).extra("itemPos", itemPos).start();
    }

    @Override
    public void initRecyclerView(TaskRVAdepter adapter) {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                mHomePresenter.onClickTask(pos);
            }

            @Override
            public boolean onLongItemClick(int pos) {
                mHomePresenter.onLongClickTask(pos, getBaseContext());
                return true;
            }
        });
        adapter.setOnCheckBoxClickListener(pos -> mHomePresenter.onClickCheckBox(pos));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecyclerView.setAdapter(adapter);
    }

    @Click(R.id.fab)
    void onViewFabClick() {
        mHomePresenter.onClickFab();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePresenter.closeDB();
    }
}

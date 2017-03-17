package com.example.alexander.todolist.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.todolist.R;
import com.example.alexander.todolist.adapters.TaskRVAdepter;
import com.example.alexander.todolist.mvp.models.Task;
import com.example.alexander.todolist.mvp.presenters.HomePresenter;
import com.example.alexander.todolist.mvp.views.HomeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;
import io.realm.RealmResults;

@EActivity(R.layout.activity_main)
public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter
    HomePresenter homePresenter;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @ViewById(R.id.rv)
    RecyclerView recyclerView;

    private Realm mRealm;


    @AfterViews
    void init() {
        initToolbar();
        initListenerFAB();
        initRecyclerView();
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
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCreateTaskActivity() {
        AddTaskActivity_.intent(this).start();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initListenerFAB() {
        fab.setOnClickListener(view -> homePresenter.onClickFab());
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TaskRVAdepter adapter = new TaskRVAdepter(initTasks());
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(adapter);
    }

    private RealmResults<Task> initTasks() {
        Realm.init(getBaseContext());
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmResults<Task> tasks = mRealm.where(Task.class).findAll();
        mRealm.commitTransaction();
        return tasks;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}

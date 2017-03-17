package com.example.alexander.todolist.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.alexander.todolist.R;
import com.example.alexander.todolist.mvp.presenters.HomePresenter;
import com.example.alexander.todolist.mvp.views.HomeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class HomeActivity extends MvpAppCompatActivity implements HomeView {

    @InjectPresenter
    HomePresenter homePresenter;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @AfterViews
    void init() {
        initToolbar();
        initListenerFAB();
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
        CreateTaskActivity_.intent(this).start();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initListenerFAB() {
        fab.setOnClickListener(view -> homePresenter.onClickFab());
    }
}
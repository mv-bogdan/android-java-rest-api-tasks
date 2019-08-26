package com.example.android_java_rest_api_tasks.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_java_rest_api_tasks.R;
import com.example.android_java_rest_api_tasks.adapters.TasksAdapter;
import com.example.android_java_rest_api_tasks.models.TaskModel;
import com.example.android_java_rest_api_tasks.models.TasksModel;
import com.example.android_java_rest_api_tasks.net.NetClient;
import com.example.android_java_rest_api_tasks.net.NetInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final int INTENT_REQUEST = 123;
    public static final String TITLE = "title";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String IS_EDIT = "isEdit";
    public static final String IS_COMPLETED = "isCompleted";

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private int CurrentBottomMenuElement;


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setupAdapter();
    }

        public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                startActivityForResult(new Intent(this, AddItemActivity.class), INTENT_REQUEST);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_active_tasks:
                    CurrentBottomMenuElement = 0;
                    //realmAdapter.onChangeCurrentBottomMenu();
                    //setupAdapter();
                    return true;
                case R.id.navigation_completed_tasks:
                    CurrentBottomMenuElement = 1;
                    //realmAdapter.onChangeCurrentBottomMenu();
                    //setupAdapter();
                    return true;
            }
            return false;
        }
    };

    private void setupAdapter() {
        if (CurrentBottomMenuElement == 0) {
            adapter = new TasksAdapter(this, new RealmController(this).getActiveTasks());
        } else if (CurrentBottomMenuElement == 1) {
            adapter = new TasksAdapter(this, new RealmController(this).getCompletedTasks());
        }
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setupAdapter();
    }

    @Override
    public void onTaskLayoutClick(int id, String title, long dateComplete, boolean isCompleted) {
       // TasksNotifications.createNotification(this);
        Intent intent = new Intent(this, AddItemActivity.class);
        intent.putExtra(IS_EDIT, true);
        intent.putExtra(ID, id);
        intent.putExtra(TITLE, title);
        intent.putExtra(DATE, dateComplete);
        intent.putExtra(IS_COMPLETED, isCompleted);
        startActivity(intent);
    }
}

    @Override
    protected void onResume() {
        super.onResume();
        getObservableViewTasks().subscribe(getObserverViewTasks());
    }

    public Observable<TasksModel> getObservableViewTasks(){
        return NetClient.getRetrofit().create(NetInterface.class)
                .getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<TasksModel> getObserverViewTasks(){
        return new DisposableObserver<TasksModel>() {

            @Override
            public void onNext(@io.reactivex.annotations.NonNull TasksModel taskResponse) {
                List<TaskModel> tasks = taskResponse.getData();
                recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                adapter = new TasksAdapter(tasks, getBaseContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
            }

            @Override
            public void onComplete() {

            }

        };
    }

}

//public class MainActivity extends AppCompatActivity implements RealmAdapter.OnClickListener {
//
//    private static final int INTENT_REQUEST = 123;
//
//    public static final String TITLE = "title";
//    public static final String ID = "id";
//    public static final String DATE = "date";
//    public static final String IS_EDIT = "isEdit";
//    public static final String IS_COMPLETED = "isCompleted";
//
//    @BindView(R.id.listView)
//    ListView recyclerView;
//
//    @BindView(R.id.nav_view)
//    BottomNavigationView navView;
//
//    private TasksAdapter tasksAdapter;
//
//    private int CurrentBottomMenuElement;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        setupAdapter();
//       // createNotificationChannel();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.addItem:
//                startActivityForResult(new Intent(this, AddItemActivity.class), INTENT_REQUEST);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_active_tasks:
//                    CurrentBottomMenuElement = 0;
//                    //realmAdapter.onChangeCurrentBottomMenu();
//                    //setupAdapter();
//                    return true;
//                case R.id.navigation_completed_tasks:
//                    CurrentBottomMenuElement = 1;
//                    //realmAdapter.onChangeCurrentBottomMenu();
//                    //setupAdapter();
//                    return true;
//            }
//            return false;
//        }
//    };
//
//    private void setupAdapter() {
//        if (CurrentBottomMenuElement == 0) {
//            tasksAdapter = new TasksAdapter(this, new RealmController(this).getActiveTasks());
//        } else if (CurrentBottomMenuElement == 1) {
//            tasksAdapter = new TasksAdapter(this, new RealmController(this).getCompletedTasks());
//        }
//        tasksAdapter.setOnClickListener(this);
//        recyclerView.setAdapter(tasksAdapter);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        setupAdapter();
//    }
//
//    @Override
//    public void onTaskLayoutClick(int id, String title, long dateComplete, boolean isCompleted) {
//       // TasksNotifications.createNotification(this);
//        Intent intent = new Intent(this, AddItemActivity.class);
//        intent.putExtra(IS_EDIT, true);
//        intent.putExtra(ID, id);
//        intent.putExtra(TITLE, title);
//        intent.putExtra(DATE, dateComplete);
//        intent.putExtra(IS_COMPLETED, isCompleted);
//        startActivity(intent);
//    }
//}
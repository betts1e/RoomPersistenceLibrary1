package com.example.betti.roompersistencelibrary;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner{

    @BindView(R.id.user_list)
    RecyclerView userListView;

    private UserRepository userRepository;
    private BookRepository bookRepository;
    private UserAdapter userAdapter;
    private List<UserBooks> userBooks;
    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        userBooks = new ArrayList<>();
        userAdapter = new UserAdapter(userBooks);
        userRepository = new UserRepository((LibApp) getApplicationContext());
        bookRepository = new BookRepository((LibApp) getApplicationContext());


        userRepository.insertUser();
        bookRepository.insertBooks();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        userListView.setLayoutManager(layoutManager);
        userListView.setAdapter(userAdapter);

        updateUserList();
    }

    private void updateUserList() {
        bookRepository.getBookByUserId().observe(this, new Observer<List<UserBooks>>() {
            @Override
            public void onChanged(@Nullable List<UserBooks> newUserBooks) {
                userAdapter.setUsers(newUserBooks);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        String[] state = new String[]{"New York", "New Mexico"};
        switch (id) {
            case R.id.actio_delete:

                userRepository.deleteUserByState(state).observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer aLong) {
                        Toast.makeText(MainActivity.this, "User Deleted " + aLong, Toast.LENGTH_SHORT).show();
                        updateUserList();
                    }
                });
                break;
            case R.id.action_update:
                userRepository.updateAddressByState(state).observe(this, new Observer<Long>() {
                    @Override
                    public void onChanged(@Nullable Long aLong) {
                        Toast.makeText(MainActivity.this, "User Update " + aLong, Toast.LENGTH_SHORT).show();
                        updateUserList();
                    }
                });
                break;
        }
        return false;
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}

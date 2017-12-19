package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import java.text.ParseException;
import java.util.List;

public class UserRepository {

    private AppDatabase appDatabase;
    private DatabaseCreator databaseCreator;

    UserRepository(LibApp libApp) {
        appDatabase = Room.databaseBuilder(libApp, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
        databaseCreator = new DatabaseCreator();
    }


    @SuppressLint("StaticFieldLeak")
    void insertUser() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    appDatabase.userDao().insertAll(databaseCreator.getRandomUserList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    LiveData<Integer> deleteUserByState(final String... state) {
        final MutableLiveData<Integer> longMutableLiveData = new MutableLiveData<>();

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                return appDatabase.userDao().deleteUserByState(state);
            }

            @Override
            protected void onPostExecute(Integer aLong) {
                super.onPostExecute(aLong);
                longMutableLiveData.setValue(aLong);
            }
        }.execute();

        return longMutableLiveData;
    }

    public LiveData<List<User>> getAllUser() {
        return appDatabase.userDao().fetchAllUser();
    }

    @SuppressLint("StaticFieldLeak")
    LiveData<Long> updateAddressByState(final String[] state) {
        final MutableLiveData<Long> longMutableLiveData = new MutableLiveData<>();
        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                return appDatabase.userDao().updateAddressByState(state);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                longMutableLiveData.setValue(aLong);
            }
        }.execute();
        return longMutableLiveData;
    }

}

package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.SystemClock;

import java.util.List;

public class BookRepository {

    private DatabaseCreator databaseCreator;
    private AppDatabase appDatabase;

    private BookDao bookDao;
    public BookRepository(LibApp libApp) {
        this.databaseCreator = new DatabaseCreator();
        appDatabase= Room.databaseBuilder(libApp,AppDatabase.class,AppDatabase.DATABASE_NAME).build();

        bookDao=appDatabase.bookDao();
    }

    public LiveData<Boolean> insertBooks(){
        final MutableLiveData<Boolean> booleanMutableLiveData=new MutableLiveData<>();
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                bookDao.insertBooks(databaseCreator.getRandomBookList());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                booleanMutableLiveData.setValue(true);
            }
        }.execute();
        return booleanMutableLiveData;
    }


    public LiveData<List<UserBooks>> getBookByUserId(){
        return bookDao.fetchBookBorrower();
    }
}

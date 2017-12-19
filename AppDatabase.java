package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {User.class, Book.class}, version = 1)
abstract public class AppDatabase extends RoomDatabase {

    public static String DATABASE_NAME = "LibraryDb";

    public abstract UserDao userDao();

    public abstract BookDao bookDao();


}

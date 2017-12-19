package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.app.Application;

public class LibApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        deleteDatabase(AppDatabase.DATABASE_NAME);
    }
}

package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.arch.persistence.room.Embedded;

public class UserWithAge {

    @Embedded
    public User user;

    public int age;
}

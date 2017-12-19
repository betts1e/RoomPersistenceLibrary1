package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Insert
    void insertBooks(List<Book> books);

    @Query("SELECT user.*,count(book.user_id) as borrowe FROM user LEFT JOIN book ON user.user_id = book.user_id group by user.user_id,book.user_id")
    LiveData<List<UserBooks>> fetchBookBorrower();

}

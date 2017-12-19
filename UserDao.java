package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> user);

    @Query("update user set street=:street where state in(:state)")
    long updateAddressByState(String[] state);

    @Query("delete from user where state in (:state)")
    int deleteUserByState(String... state);


    @Query("delete from user where user_id=:id")
    int deleteUser(int id);

    @Query("select * from user where user_id=:id")
    LiveData<User> fetchUserByUserId(int id);

    @Query("select * from user where date_of_birth=Date(:date)")
    LiveData<List<User>> fetchUserByUserDOB(String date);

    @Query("select * from user")
    LiveData<List<User>> fetchAllUser();

    @Query("SELECT * FROM User WHERE date_of_birth BETWEEN date(:from) AND date(:to)")
    LiveData<List<User>> fetchUserBetweenDate(String from, String to);


    @Query("SELECT strftime('%Y', date_of_birth) as year,count(date_of_birth) as count FROM User GROUP BY date_of_birth")
    LiveData<List<GroupByUtil>> groupByUserDOBYear();

    @Query("SELECT * FROM User WHERE strftime('%Y', date_of_birth) = :year")
    LiveData<List<User>> fetchUserByDOBYear();

    @Query("select * from User where created_date>=datetime('now', :duration)")
    LiveData<List<User>> fetchUserByDuration();

    @Query("select * from User ORDER BY date(date_of_birth) asc")
    LiveData<List<User>> fetchUserOrderByDOB();

    @Query("SELECT *, cast(strftime('%Y.%m%d', 'now') - strftime('%Y.%m%d', date_of_birth) as int) as age FROM User")
    LiveData<List<UserWithAge>> fetchUserWithAge();
}


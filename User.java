package com.example.betti.roompersistencelibrary;

/**
 * Created by Betti on 19.12.17.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity(indices = {@Index(value = "first_name")})
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public long userId;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "created_date")
    @TypeConverters({TimestampConverter.class})
    public Date createDate;

    @ColumnInfo(name = "date_of_birth")
    @TypeConverters({DateConverter.class})
    public Date dob;

    @Embedded
    public Address address;
}

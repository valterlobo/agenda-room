package com.valterlobo.agenda;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id=:id ")
    User getById(int id);


    @Insert
    void insertAll(User... users);

    @Delete
    void removeAll(User... users);

    @Update
    void updateAll(User... users);

}

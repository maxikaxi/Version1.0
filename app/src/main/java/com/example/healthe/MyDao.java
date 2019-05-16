package com.example.healthe;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addUser(User user);

  @Query("select * from parameters")
    public List<User> getUsers();


}

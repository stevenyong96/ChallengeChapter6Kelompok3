package com.example.challengechapter6kelompok3.dao

import androidx.room.*
import com.example.challengechapter6kelompok3.entity.Users

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUser() : List<Users>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: Users) : Long

    @Update
    fun updateUser(user: Users) : Int

    @Delete
    fun deleteUser(user: Users) : Int

    @Query("SELECT * FROM users WHERE username = :username and password = :password")
    fun checkUser(username: String, password: String): Long

}
package com.example.loginsignin_page.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getAllPosotion(): LiveData<List<User>>

    @Query("SELECT firstName FROM user_table")
    fun getFirstName(): LiveData<String>

    @Query("SELECT lastName FROM user_table")
    fun getLastName(): LiveData<String>

    @Query("SELECT userName FROM user_table")
    fun getUserName(): LiveData<String>

    @Query("SELECT password FROM user_table")
    fun getPassword(): LiveData<String>

    @Query("SELECT dateOfBirth FROM user_table")
    fun getDateOfBirth(): LiveData<String>

    @Query("SELECT userName FROM user_table WHERE userName = :userName ")
    fun getUserNameChecked(userName: String): LiveData<String>

    @Query("SELECT * FROM user_table WHERE password = :password ")
    fun getPasswordChecked(password: String): LiveData<String>
}
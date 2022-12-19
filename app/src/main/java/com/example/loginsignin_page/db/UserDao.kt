package com.example.loginsignin_page.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user: Users)

    @Delete
    fun deleteUser(user: Users)

    @Query("SELECT * FROM users_table")
    fun getAll(): List<Users>

    @Query("SELECT EXISTS (SELECT * FROM users_table WHERE userName = :userName)")
    fun checkedUserName(userName: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM users_table WHERE userName = :userName AND password = :password)")
    fun checkedLogin(userName: String, password: String): Boolean
}
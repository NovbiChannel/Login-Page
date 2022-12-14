package com.example.loginsignin_page.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)

abstract class UserDatabase: RoomDatabase() {

    abstract fun getUserDao(): UserDao
}
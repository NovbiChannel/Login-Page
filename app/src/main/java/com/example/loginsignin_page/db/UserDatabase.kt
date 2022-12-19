package com.example.loginsignin_page.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginsignin_page.other.Constants.USER_DATABASE_NAME

@Database(
    entities = [Users::class],
    version = 1,
)

abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    USER_DATABASE_NAME)
                    .addMigrations()
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}
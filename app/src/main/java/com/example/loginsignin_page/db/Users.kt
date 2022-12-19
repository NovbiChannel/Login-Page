package com.example.loginsignin_page.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class Users (

    @ColumnInfo(name = "firstName") var firstName: String?,
    @ColumnInfo(name = "lastName") var lastName: String?,
    @ColumnInfo(name = "userName") var userName: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "dateOfBirth") var dateOfBirth: String?,
        ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
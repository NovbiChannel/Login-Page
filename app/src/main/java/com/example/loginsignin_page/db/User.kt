package com.example.loginsignin_page.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    var firstName: String = "",
    var lastName: String = "",
    var userName: String = "",
    var password: String = "",
    var dateOfBirth: String = "",
        ){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
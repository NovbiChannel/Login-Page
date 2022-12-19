package com.example.loginsignin_page.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginsignin_page.db.UserDao

class MainViewModelFactory (
    private val userDao: UserDao
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(userDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
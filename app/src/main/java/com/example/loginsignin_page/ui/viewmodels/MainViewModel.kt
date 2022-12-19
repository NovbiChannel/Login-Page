package com.example.loginsignin_page.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.loginsignin_page.db.UserDao
import com.example.loginsignin_page.db.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel (private val userDao: UserDao): ViewModel() {

    suspend fun addNewUser(user: Users) = withContext(Dispatchers.IO) {
        userDao.insertAll(user)
    }

    fun fullUsers(): List<Users> = userDao.getAll()

    suspend fun userNameChecked(userName: String) = withContext(Dispatchers.IO) {
        userDao.checkedUserName(userName)
    }

    suspend fun userLoginChecked(userName: String, password: String) = withContext(Dispatchers.IO) {
        userDao.checkedLogin(userName, password)
    }

    suspend fun searchIdUser(userName: String, password: String) = withContext(Dispatchers.IO) {
        userDao.searchIdUser(userName, password)
    }

    suspend fun searchFirstName(id: Int) = withContext(Dispatchers.IO) {
        userDao.searchFirstName(id)
    }
}
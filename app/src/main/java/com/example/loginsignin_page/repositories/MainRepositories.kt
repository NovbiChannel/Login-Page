package com.example.loginsignin_page.repositories

import com.example.loginsignin_page.db.User
import com.example.loginsignin_page.db.UserDao
import com.example.loginsignin_page.ui.fragments.LoginFragment
import com.example.loginsignin_page.ui.fragments.SignupFragment
import javax.inject.Inject

class MainRepositories @Inject constructor(
    val userDao: UserDao,
    val userData: LoginFragment
) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun deleteUSer(user: User) = userDao.deleteUser(user)

    fun getAllPos() = userDao.getAllPosotion()

    fun getFirstName() = userDao.getFirstName()

    fun getLastName() = userDao.getLastName()

    fun getUserName() = userDao.getUserName()

    fun getPassword() = userDao.getPassword()

    fun getDateOfBirth() = userDao.getDateOfBirth()

    fun getUserNameChecked() = userDao.getUserNameChecked(userData.getUserName().toString())

    fun getPasswordChecked() = userDao.getPasswordChecked(userData.getUserPassword().toString())
}
package com.example.loginsignin_page.ui

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsignin_page.db.User
import com.example.loginsignin_page.repositories.MainRepositories
import com.example.loginsignin_page.ui.fragments.LoginFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepositories: MainRepositories
): ViewModel() {

    val user = MediatorLiveData<List<User>>()

    init {
        user
    }

    fun insertUser(user: User) = viewModelScope.launch {
        mainRepositories.insertUser(user)
    }

    fun userNameChecked(userName: LoginFragment) = viewModelScope.launch {
        mainRepositories.getUserNameChecked()
    }
    fun userPasswordChecked() = viewModelScope.launch {
        mainRepositories.getPasswordChecked()
    }

}
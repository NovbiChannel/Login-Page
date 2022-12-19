package com.example.loginsignin_page.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.loginsignin_page.BaseApplication
import com.example.loginsignin_page.databinding.ActivityMenuBinding
import com.example.loginsignin_page.other.Constants.USER_FIRST_NAME
import com.example.loginsignin_page.ui.viewmodels.MainViewModel
import com.example.loginsignin_page.ui.viewmodels.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userFirstName = intent.getStringExtra(USER_FIRST_NAME)

        binding.tvHelloUser.text = "Hello $userFirstName"
    }
}
package com.example.loginsignin_page.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.loginsignin_page.BaseApplication
import com.example.loginsignin_page.databinding.ActivityLoginBinding
import com.example.loginsignin_page.ui.viewmodels.MainViewModel
import com.example.loginsignin_page.ui.viewmodels.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as BaseApplication).db.userDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            
            lifecycleScope.launch {
            val userName = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()

                if (viewModel.userLoginChecked(userName, password)) {
                    Snackbar.make(
                        binding.root,
                        "We greet you $userName",
                        Snackbar.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Snackbar.make(
                        binding.root,
                        "Invalid username or password, please check the entered data",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.tvDontHaveAnAccount.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}
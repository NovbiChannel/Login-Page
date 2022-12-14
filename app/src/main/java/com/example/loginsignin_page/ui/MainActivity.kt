package com.example.loginsignin_page.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.loginsignin_page.R
import com.example.loginsignin_page.databinding.ActivityMainBinding
import com.example.loginsignin_page.other.Constants.ACTION_SHOW_SIGNUP_FRAGMENT
import com.example.loginsignin_page.ui.fragments.SignupFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        if (intent?.action == ACTION_SHOW_SIGNUP_FRAGMENT) {
            navController.navigate(R.id.action_global_add_signupFragment)
        }
    }
}
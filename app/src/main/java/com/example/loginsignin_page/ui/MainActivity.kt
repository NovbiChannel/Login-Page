package com.example.loginsignin_page.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.loginsignin_page.databinding.ActivityMenuBinding
import com.example.loginsignin_page.other.Constants.USER_FIRST_NAME
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userFirstName = intent.getStringExtra(USER_FIRST_NAME)
        val time = LocalTime.now()

        for (i in 0..5) {
            if (i == time.hour) {
                binding.tvHelloUser.text = "Goodnight, $userFirstName"
            }
        }

        for (i in 6..10) {
            if (i == time.hour) {
                binding.tvHelloUser.text = "Good morning, $userFirstName"
            }
        }

        for (i in 11..17) {
            if (i == time.hour) {
                binding.tvHelloUser.text = "Good afternoon, $userFirstName"
            }
        }

        for (i in 18..23) {
            if (i == time.hour) {
                binding.tvHelloUser.text = "Good evening, $userFirstName"
            }
        }
    }
}
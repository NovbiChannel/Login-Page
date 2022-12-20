package com.example.loginsignin_page.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.loginsignin_page.R
import com.example.loginsignin_page.databinding.ActivityMenuBinding
import com.example.loginsignin_page.other.Constants.USER_FIRST_NAME
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    lateinit var auth: FirebaseAuth
    private lateinit var userName: String
    @RequiresApi(Build.VERSION_CODES.O)
    private val time = LocalTime.now().hour

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        userName = ""

        val userFirstName = intent.getStringExtra(USER_FIRST_NAME)
        val userNameGoogle = auth.currentUser?.displayName.toString()

        val userImgGoogle = auth.currentUser?.photoUrl

        if (userFirstName == null) {
            userName = userNameGoogle
            Picasso.get().load(userImgGoogle).into(binding.ivUserPhoto)
        } else {
            userName = userFirstName
        }

        binding.ivCalendar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        greetingsText()
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun greetingsText() {

        for (i in 0..5) {
            if (i == time) {
                binding.tvHelloUser.text = "Goodnight, $userName"
            }
        }

        for (i in 6..10) {
            if (i == time) {
                binding.tvHelloUser.text = "Good morning, $userName"
            }
        }

        for (i in 11..17) {
            if (i == time) {
                binding.tvHelloUser.text = "Good afternoon, $userName"
            }
        }

        for (i in 18..23) {
            if (i == time) {
                binding.tvHelloUser.text = "Good evening, $userName"
            }
        }
    }
}
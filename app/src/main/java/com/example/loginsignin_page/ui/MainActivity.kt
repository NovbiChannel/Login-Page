package com.example.loginsignin_page.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginsignin_page.databinding.ActivityMenuBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
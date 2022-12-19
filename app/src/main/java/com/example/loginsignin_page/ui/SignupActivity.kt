package com.example.loginsignin_page.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.loginsignin_page.BaseApplication
import com.example.loginsignin_page.databinding.ActivitySignupBinding
import com.example.loginsignin_page.db.Users
import com.example.loginsignin_page.ui.viewmodels.MainViewModel
import com.example.loginsignin_page.ui.viewmodels.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class SignupActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as BaseApplication).db.userDao()
        )
    }
    private var isAllowed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.etUserName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //NO OP
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //NO OP
                }

                override fun afterTextChanged(s: Editable?) {

                    lifecycleScope.launch {
                        val userNameValidation = s.toString()
                        if (viewModel.userNameChecked(userNameValidation)) {
                            isAllowed = false
                            Snackbar.make(
                                binding.root,
                                "This username already exists, please try another username",
                                Snackbar.LENGTH_LONG
                            ).show()
                        } else {
                            isAllowed = true
                        }
                    }
                }
            })

        binding.btnSignUp.setOnClickListener {

            lifecycleScope.launch{
                val firstName = binding.etFirstName.text.toString()
                val lastName = binding.etLastName.text.toString()
                val userName = binding.etUserName.text.toString()
                val password = binding.etPassword.text.toString()
                val dateOfBirth = binding.etDateOfBirth.text.toString()

                val user = Users(firstName, lastName, userName, password, dateOfBirth)

                if (isAllowed) {
                    viewModel.addNewUser(user)
                    startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                } else {
                    Snackbar.make(
                        binding.root,
                        "This username already exists, please try another username",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.tvAllreadyHaveOnAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
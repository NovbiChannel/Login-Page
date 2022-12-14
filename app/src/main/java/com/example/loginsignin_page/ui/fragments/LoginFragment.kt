package com.example.loginsignin_page.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.loginsignin_page.R
import com.example.loginsignin_page.databinding.FragmentLoginBinding
import com.example.loginsignin_page.db.User
import com.example.loginsignin_page.db.UserDatabase
import com.example.loginsignin_page.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.loginsignin_page.other.Constants.KEY_PASSWORD
import com.example.loginsignin_page.other.Constants.KEY_USER_NAME
import com.example.loginsignin_page.repositories.MainRepositories
import com.example.loginsignin_page.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@Suppress("UNUSED_VALUE")
@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSignUp.setOnClickListener {
            val success = sharedPref()
            if (success) {
                findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
                Timber.d("Click for src, locate to SignUp fragment")
            }
        }

        binding.btnLogin.setOnClickListener {
            authorization()
        }
    }

    fun getUserName() {
        binding.etUserName.text.toString()
    }
    fun getUserPassword(){
        binding.etPassword.text.toString()
    }

    @Suppress("UNREACHABLE_CODE")
    private fun sharedPref(): Boolean {
        if (KEY_FIRST_TIME_TOGGLE.toBoolean()) {
            return false
            Timber.d("First start is true")
        } else {
            sharedPref.edit()
                .putBoolean(KEY_FIRST_TIME_TOGGLE, true)
                .apply()
            Timber.d("First start is false")
            return true
        }
    }
    private fun authorization(): Boolean {

        val userName = binding.etUserName.text.toString()
        val password = binding.etPassword.text.toString()

        if (userName == viewModel.userNameChecked()) {
            findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            return true
        } else {
            Snackbar.make(requireView(), "Incorrect username or password entered", Snackbar.LENGTH_SHORT).show()
            return false
        }
    }
}
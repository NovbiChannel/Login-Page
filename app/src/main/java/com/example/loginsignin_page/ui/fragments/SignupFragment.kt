package com.example.loginsignin_page.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import com.example.loginsignin_page.R
import com.example.loginsignin_page.databinding.FragmentSignupBinding
import com.example.loginsignin_page.db.User
import com.example.loginsignin_page.other.Constants.KEY_DATE_OF_BIRTH
import com.example.loginsignin_page.other.Constants.KEY_FIRST_NAME
import com.example.loginsignin_page.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.loginsignin_page.other.Constants.KEY_LAST_NAME
import com.example.loginsignin_page.other.Constants.KEY_PASSWORD
import com.example.loginsignin_page.other.Constants.KEY_USER_NAME
import com.example.loginsignin_page.ui.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment: Fragment(R.layout.fragment_signup) {

    private lateinit var binding: FragmentSignupBinding

    private val viewModel: MainViewModel by viewModels()

    private val user = User()

    @Inject
    lateinit var sharedPref: SharedPreferences

    @set: Inject
    var isFirstAppOpen = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel.mainRepositories.getAllPos()

        Timber.d("${KEY_FIRST_TIME_TOGGLE.toBoolean()}")

//        if (!isFirstAppOpen) {
//            val navOption = NavOptions.Builder()
//                .setPopUpTo(R.id.signupFragment, true)
//                .build()
//            findNavController().navigate(
//                R.id.action_signupFragment_to_loginFragment,
//                savedInstanceState,
//                navOption
//            )
//        } else {
//            Timber.d("cool")
//        }

        binding.btnSignUp.setOnClickListener {
            val success = writePersonalDataToSharedPref()
            if (success) {
                Snackbar.make(
                    requireActivity().findViewById(R.id.rootView),
                    "User save successfully",
                    Snackbar.LENGTH_LONG
                ).show()
                findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            } else {
                Snackbar.make(requireView(), "Please enter all the fields", Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.tvAllreadyHaveOnAccount.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }

    private fun writePersonalDataToSharedPref(): Boolean {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val userName = binding.etUserName.text.toString()
        val password = binding.etPassword.text.toString()
        val dateOfBirth = binding.etDateOfBirth.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty()
            || password.isEmpty() || dateOfBirth.isEmpty()) {
            return false
        }
        sharedPref.edit()
            .putString(KEY_FIRST_NAME, firstName)
            .putString(KEY_LAST_NAME, lastName)
            .putString(KEY_USER_NAME, userName)
            .putString(KEY_PASSWORD, password)
            .putString(KEY_DATE_OF_BIRTH, dateOfBirth)
            .putBoolean(KEY_FIRST_TIME_TOGGLE, false)
            .apply()
        val user = User(firstName, lastName, userName, password, dateOfBirth)
        viewModel.insertUser(user)
        return true
    }
}
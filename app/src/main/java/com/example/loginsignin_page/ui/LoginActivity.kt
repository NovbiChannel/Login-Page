package com.example.loginsignin_page.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.loginsignin_page.BaseApplication
import com.example.loginsignin_page.R
import com.example.loginsignin_page.databinding.ActivityLoginBinding
import com.example.loginsignin_page.other.Constants.USER_FIRST_NAME
import com.example.loginsignin_page.ui.viewmodels.MainViewModel
import com.example.loginsignin_page.ui.viewmodels.MainViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var launcher: ActivityResultLauncher<Intent>
    lateinit var auth: FirebaseAuth
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (application as BaseApplication).db.userDao()
        )
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {
                Timber.d("Api Exception")
            }
        }

        binding.ivGoogle.setOnClickListener {
            signInWithGoogle()
        }

        binding.btnLogin.setOnClickListener {

            lifecycleScope.launch {
            val userName = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()

                if (viewModel.userLoginChecked(userName, password)) {
                    val userIdSearch = viewModel.searchIdUser(userName, password)
                    val userFirstName = viewModel.searchFirstName(userIdSearch)
                    Snackbar.make(
                        binding.root,
                        "We greet you $userName",
                        Snackbar.LENGTH_LONG
                    ).show()

                    val i = Intent(this@LoginActivity, MainActivity::class.java)
                    i.putExtra(USER_FIRST_NAME, userFirstName)
                    startActivity(i)

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

    private fun getClient(): GoogleSignInClient {

        val signInRequest = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this, signInRequest)
    }

    private fun signInWithGoogle() {
        val signInClient = getClient()
        launcher.launch(signInClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Snackbar.make(
                    binding.root,
                    "You have successfully logged in",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Snackbar.make(
                    binding.root,
                    "Authorisation Error",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}
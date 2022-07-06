package com.example.challengechapter6kelompok3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityLoginBinding
import com.example.challengechapter6kelompok3.viewModel.MainViewModel
import com.example.challengechapter6kelompok3.viewModel.MyViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LoginActivity  : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    var dataBase : UserDatabase? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = UserDatabase.getInstance(this)

        //instance of database
        val userDatabase = UserDatabase.getInstance(this)
        val myViewModelFactory = MyViewModelFactory(userDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)

        val isLandingPageShown = SharedPrefManager.getIsLandingPageShown(this)
        Log.d(SplashActivity::class.simpleName,"LOGIN : ${isLandingPageShown}")


        val intentToHome = Intent(this, MainActivity::class.java)


        binding.ivRegister.setOnClickListener {
            val intentToRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentToRegister)
        }

        binding.loginButton.setOnClickListener {
            Log.d(LoginActivity::class.simpleName,"Login Start")
            var username = binding.etUsername.text.toString()
            var password = binding.etPassword.text.toString()
            GlobalScope.async {
                var listUser = dataBase?.userDao()?.getAllUser()
                Log.d(LoginActivity::class.simpleName,"List User: "+listUser.toString())
                Log.d(LoginActivity::class.simpleName,"Username : "+ username)
                val result = dataBase?.userDao()?.checkUser(username,password)
                Log.d(this@LoginActivity::class.simpleName,"Result Check User :" + result.toString())
                runOnUiThread {
                    if(result != 0) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login Validation Success for ${username}",
                            Toast.LENGTH_SHORT
                        ).show()
                        SharedPrefManager.setIsLandingPageShown(this@LoginActivity, false)
                        intentToHome.putExtra("DATA_USER_USERNAME", binding.etUsername.getText().toString())
                        //intentToHome.putExtra("DATA_USER_PASSWORD", binding.etPassword.getText().toString())
                        binding.etUsername.setText("")
                        binding.etPassword.setText("")
                        startActivity(intentToHome)
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Login Validation Error ${username}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
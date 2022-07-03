package com.example.challengechapter6kelompok3

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LoginActivity  : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    var dataBase : UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = UserDatabase.getInstance(this)

        val isLandingPageShown = SharedPrefManager.getIsLandingPageShown(this)
        Log.d(SplashActivity::class.simpleName,"LOGIN : ${isLandingPageShown}")

        val username = binding.etUsername.text.toString()
        val password = binding.etPassword.text.toString()
        var status = 0

        binding.loginButton.setOnClickListener {
//            Log.d(LoginActivity::class.simpleName,"Test")
//            status = 0
//            GlobalScope.async {
//                val result = dataBase?.userDao()?.checkUser(username,password)
//                runOnUiThread {
//                    if(result != 0.toLong()) {
//                        Toast.makeText(
//                            this@LoginActivity,
//                            "User Validation Success for ${username}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        status =1
//                    } else {
//                        Toast.makeText(
//                            this@LoginActivity,
//                            "User Validation Error ${username}",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        status=0
//                    }
//
//                    finish()
//                }
//            }
//
//            Log.d(LoginActivity::class.simpleName,status.toString())
//
//            if(status == 1){
//                SharedPrefManager.setIsLandingPageShown(this, false)
//                val intentToHome = Intent(this, MainActivity::class.java)
//                intentToHome.putExtra("DATA_USER_NAME", binding.etUsername.getText().toString())
//                startActivity(intentToHome)
//            }


            SharedPrefManager.setIsLandingPageShown(this, false)
            val intentToHome = Intent(this, MainActivity::class.java)
            intentToHome.putExtra("DATA_USER_NAME", binding.etUsername.getText().toString())
            startActivity(intentToHome)

        }
    }
}
package com.example.challengechapter6kelompok3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityAddUserBinding
import com.example.challengechapter6kelompok3.entity.Users
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddUserBinding

    var dataBase : UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = UserDatabase.getInstance(this)


        binding.btnSubmit.setOnClickListener {
        }
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val nama = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()

            val tempUser = Users(null, username, password,nama,email)

            GlobalScope.async {
                val result = dataBase?.userDao()?.insertUser(tempUser)
                runOnUiThread {
                    if(result != 0.toLong()) {
                        Toast.makeText(
                            this@AddUserActivity,
                            "Sukses menambahkan ${tempUser.nama}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@AddUserActivity,
                            "Gagal menambahkan ${tempUser.nama}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    finish()
                }
            }


        }


    }

package com.example.challengechapter6kelompok3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityAddUserBinding
import com.example.challengechapter6kelompok3.entity.Users
import com.example.challengechapter6kelompok3.presenter.AddUserPresenterImp
import com.example.challengechapter6kelompok3.presenter.AddUserView
import com.example.challengechapter6kelompok3.presenter.MainPresenterImp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddUserActivity : AppCompatActivity() ,AddUserView{

    lateinit var binding: ActivityAddUserBinding
    lateinit var presenter: AddUserPresenterImp
    var dataBase : UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = AddUserPresenterImp(this)
        dataBase = UserDatabase.getInstance(this)


        binding.btnSubmit.setOnClickListener {
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val nama = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            println(username)
            println(password)
            println(nama)
            println(email)

            val tempUser = Users(username, password, nama, email)

            GlobalScope.async {
                val result = dataBase?.userDao()?.insertUser(tempUser)
                runOnUiThread {
                    if (result != 0.toLong()) {
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
    }

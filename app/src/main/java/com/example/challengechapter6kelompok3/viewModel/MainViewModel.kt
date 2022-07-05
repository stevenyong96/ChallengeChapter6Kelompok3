package com.example.challengechapter6kelompok3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.entity.Users
import kotlinx.coroutines.launch

class MainViewModel(private val userDatabase: UserDatabase) : ViewModel() {

    val notes = userDatabase.userDao().getAllUser()

    fun insertUser(userEntity: Users){
        viewModelScope.launch{
            userDatabase.userDao().insertUser(userEntity)
        }
    }
}
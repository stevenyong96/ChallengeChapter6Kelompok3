package com.example.challengechapter6kelompok3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter6kelompok3.database.UserDatabase

class MyViewModelFactory constructor(private val  userDatabase: UserDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(userDatabase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
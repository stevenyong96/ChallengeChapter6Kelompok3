package com.example.challengechapter6kelompok3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challengechapter6kelompok3.databinding.ActivityAddUserBinding
import com.example.challengechapter6kelompok3.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
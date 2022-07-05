package com.example.challengechapter6kelompok3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.challengechapter6kelompok3.databinding.ActivityCheckoutPaymentBinding

class CheckoutPaymentActivity : AppCompatActivity() {

    lateinit var binding: ActivityCheckoutPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
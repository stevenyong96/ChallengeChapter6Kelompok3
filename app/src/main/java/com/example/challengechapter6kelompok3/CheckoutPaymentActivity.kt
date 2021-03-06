package com.example.challengechapter6kelompok3

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.challengechapter6kelompok3.database.CartDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityCheckoutPaymentBinding
import com.example.challengechapter6kelompok3.entity.Users
import com.example.challengechapter6kelompok3.presenter.CartTokpeePresenterImp
import com.example.challengechapter6kelompok3.presenter.CheckoutPaymentPresenterImp
import com.example.challengechapter6kelompok3.presenter.CheckoutPaymentView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat

class CheckoutPaymentActivity : AppCompatActivity(),CheckoutPaymentView {

    lateinit var binding: ActivityCheckoutPaymentBinding
    lateinit var presenter: CheckoutPaymentPresenterImp
    var database : CartDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = CheckoutPaymentPresenterImp(this)

        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")
        val tempTotalItem = intent.getStringExtra("DATA_TOTAL_ITEM")
        val tempTotalPayment = intent.getStringExtra("DATA_TOTAL_PAYMENT")

        var intentToHome = Intent(this,MainActivity::class.java)
        var intentToCart = Intent(this,CartTokpeeActivity::class.java)
        database = CartDatabase.getInstance(this)

        binding.tvTotalPayment.setText(tempTotalPayment)
        binding.tvCustomer.setText(tempUsername)

        binding.ivBack.setOnClickListener {
            intentToCart.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToCart)
        }

        binding.btnCheckout.setOnClickListener {
            AlertDialog.Builder(this).setPositiveButton("Ya") { p0, p1 ->

                    GlobalScope.launch {

                        val result = database?.cartDao()?.deleteAllCart()

                        runOnUiThread {
                            if (result != 0) {
                                Toast.makeText(
                                    this@CheckoutPaymentActivity,
                                    "Payment sebesar ${tempTotalPayment} berhasil",
                                    Toast.LENGTH_SHORT
                                ).show()
                                intentToHome.putExtra("DATA_USER_USERNAME", tempUsername)
                                startActivity(intentToHome)
                            } else {
                                Toast.makeText(
                                    this@CheckoutPaymentActivity,
                                    "FAILED ,Payment sebesar ${tempTotalPayment} gagal",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }.setNegativeButton("Tidak") { p0, p1 ->
                    p0.dismiss()
                }.setMessage("Apakah Anda Yakin ingin membayar ${tempTotalItem} ,dengan total pembelanjaan sebesar ${tempTotalPayment}")
                .setTitle("Confirmation Payment").create()
                .show()
        }

    }
}
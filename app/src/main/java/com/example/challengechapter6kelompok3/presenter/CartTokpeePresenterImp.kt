package com.example.challengechapter6kelompok3.presenter

import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.challengechapter6kelompok3.CartTokpeeActivity
import com.example.challengechapter6kelompok3.adapter.CartItemAdapter
import com.example.challengechapter6kelompok3.database.CartDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityCartTokpeeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat

class CartTokpeePresenterImp(val view: CartTokpeeView) : CartTokpeePresenter {
    override fun getTotalItem(bindingCart : ActivityCartTokpeeBinding,cartTokpeeActivity: CartTokpeeActivity){
        var database = CartDatabase.getInstance(cartTokpeeActivity)
        GlobalScope.launch {
            val resTotalItem = database?.cartDao()?.getTotalItem()
            val resTotalPayment = database?.cartDao()?.getTotalPayment()
            runOnUiThread{
                var satuan = " Items"
                var resultQty = view.concat(resTotalItem.toString(),satuan)
                val formatter: NumberFormat = DecimalFormat("#,###")
                val formattedNumber: String = formatter.format(resTotalPayment)
                val resultPayment = view.concat("Rp ", formattedNumber)
                bindingCart.tvQty.setText(resultQty)
                bindingCart.tvItemPrice.setText(resultPayment)
            }
        }
    }

//    override fun fetchData(cartTokpeeActivity: CartTokpeeActivity, bindingCart: ActivityCartTokpeeBinding) {
//        var database = CartDatabase.getInstance(cartTokpeeActivity)
//        GlobalScope.launch {
//            val listCart = database?.cartDao()?.getAllCart()
//
//            runOnUiThread{
//                listCart?.let {
//                    val adapter = CartItemAdapter(it)
//                    bindingCart.rvListCart.adapter = adapter
//                }
//            }
//        }
//
//        bindingCart.srlStudent.isRefreshing = false
//        getTotalItem(bindingCart,cartTokpeeActivity)
//    }
}
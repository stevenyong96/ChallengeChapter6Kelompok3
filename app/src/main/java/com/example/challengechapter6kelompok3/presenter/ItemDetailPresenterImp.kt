package com.example.challengechapter6kelompok3.presenter

import android.widget.Toast
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.challengechapter6kelompok3.ItemDetailActivity
import com.example.challengechapter6kelompok3.database.CartDatabase
import com.example.challengechapter6kelompok3.entity.Carts
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ItemDetailPresenterImp(val view: ItemDetailView) : ItemDetailPresenter {
    override fun insItemDetail(param_item_name:String,param_item_price: String,paramItemColor: String, param_item_image: String, itemDetailActivity: ItemDetailActivity){
        var database = CartDatabase.getInstance(itemDetailActivity)
        val cartEntity = Carts(param_item_name,"",param_item_price?.toInt(),paramItemColor,param_item_image,1)
        GlobalScope.async {
            val result = database?.cartDao()?.insertCart(cartEntity)
            runOnUiThread {
                if(result != 0.toLong()) {
                    Toast.makeText(
                        itemDetailActivity,
                        "Sukses menambahkan ${param_item_name}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        itemDetailActivity,
                        "Gagal menambahkan ${param_item_name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
package com.example.challengechapter6kelompok3

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.challengechapter6kelompok3.database.CartDatabase
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityItemDetailBinding
import com.example.challengechapter6kelompok3.entity.Carts
import com.example.challengechapter6kelompok3.entity.Users
import com.example.challengechapter6kelompok3.presenter.CheckoutPaymentPresenterImp
import com.example.challengechapter6kelompok3.presenter.ItemDetailPresenterImp
import com.example.challengechapter6kelompok3.presenter.ItemDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.text.DecimalFormat
import java.text.NumberFormat

fun concat(s1: String, s2: String): String {
    return s1 + s2
}

class ItemDetailActivity : AppCompatActivity() ,ItemDetailView{
    lateinit var binding: ActivityItemDetailBinding
    lateinit var presenter: ItemDetailPresenterImp
    var dataBase : CartDatabase? = null
    var itemName : String ? = ""
    var itemPrice : String ? = ""
    var itemColor : String ? = ""
    var itemImage : String ? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = ItemDetailPresenterImp(this)

        dataBase = CartDatabase.getInstance(this)
         itemName= intent.getStringExtra("KEY_ITEM_NAME")
         itemPrice = intent.getStringExtra("KEY_ITEM_PRICE")
         itemColor = intent.getStringExtra("KEY_ITEM_COLOR")
         itemImage = intent.getStringExtra("KEY_ITEM_IMAGE")
         var tempUsername = intent.getStringExtra("DATA_USER_USERNAME")

        binding.tvItemName.setText(itemName)
        val formatter: NumberFormat = DecimalFormat("#,###")
        val formattedNumber: String = formatter.format(itemPrice?.toInt())
        val result = concat("Rp ", formattedNumber)

        binding.tvItemPrice.setText(result)
        binding.clParent.setBackgroundColor(Color.parseColor(itemColor))
        binding.clHdr.setBackgroundColor(Color.parseColor(itemColor))
        binding.buttonAddToCart.setBackgroundColor(Color.parseColor(itemColor))
        Glide.with(this).load(itemImage).into(binding.ivImage)

        binding.ivItemDetailBack.setOnClickListener {
            var intentToHome = Intent(this,MainActivity::class.java)
            intentToHome.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToHome)
        }

        binding.ivCartDetail.setOnClickListener {
            var intentToCart = Intent(this,CartTokpeeActivity::class.java)
            intentToCart.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToCart)
        }

        binding.buttonAddToCart.setOnClickListener {
            GlobalScope.async {
                val result = dataBase?.cartDao()?.checkCart(itemName)
                runOnUiThread {
                    if(result == 0.toLong()) {
                        itemName?.let { it1 -> itemPrice?.let { it2 ->
                            itemColor?.let { it3 ->
                                itemImage?.let { it4 ->
                                    presenter.insItemDetail(it1,
                                        it2, it3, it4,this@ItemDetailActivity)
                                }
                            }
                        } }
                    }
                    else{
                        Toast.makeText(
                            this@ItemDetailActivity,
                            "Item ${itemName} sudah ada dalam cart sebelumnya",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

//    fun insItemDetail(param_item_name:String ?= "",param_item_price: String ?="0",paramItemColor: String?="" , param_item_image: String ? =""){
//        val cartEntity = Carts(param_item_name,"",param_item_price?.toInt(),paramItemColor,param_item_image,1)
//        GlobalScope.async {
//            val result = dataBase?.cartDao()?.insertCart(cartEntity)
//            runOnUiThread {
//                if(result != 0.toLong()) {
//                    Toast.makeText(
//                        this@ItemDetailActivity,
//                        "Sukses menambahkan ${param_item_name}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        this@ItemDetailActivity,
//                        "Gagal menambahkan ${param_item_name}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }


}
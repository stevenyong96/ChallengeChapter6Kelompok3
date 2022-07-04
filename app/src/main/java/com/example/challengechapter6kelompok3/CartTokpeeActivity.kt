package com.example.challengechapter6kelompok3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter6kelompok3.adapter.CartItemAdapter
import com.example.challengechapter6kelompok3.databinding.ActivityCartTokpeeBinding
import com.example.challengechapter6kelompok3.model.DataCart


class CartTokpeeActivity : AppCompatActivity() {
    private lateinit var bindingCart : ActivityCartTokpeeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingCart = ActivityCartTokpeeBinding.inflate(layoutInflater)
        setContentView(bindingCart.root)

        bindingCart.rvListCart.adapter = CartItemAdapter(getDummyData()) {
            Toast.makeText(this, "Item di click : ${it.itemDesc}", Toast.LENGTH_SHORT).show()
        }
        bindingCart.rvListCart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        bindingCart.ivBackBtn.setOnClickListener {
            val intentToHome = Intent(this, MainActivity::class.java)
            startActivity(intentToHome)
        }

    }



    private fun getDummyData() : ArrayList<DataCart> {
        return arrayListOf(
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000,"#FF233D"),
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_nmd.png", "Adidas NMD1", 2850000,"#F5CA7B"),
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/nike_jacket.png", "Nike Jacket", 300000,"#EEEE9B"),
            DataCart("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Backpack", 950000,"#98fb98"),
        )
    }
}
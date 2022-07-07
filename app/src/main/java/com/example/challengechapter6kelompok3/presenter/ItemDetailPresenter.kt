package com.example.challengechapter6kelompok3.presenter

import android.widget.Toast
import com.example.challengechapter6kelompok3.ItemDetailActivity
import com.example.challengechapter6kelompok3.entity.Carts
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

interface ItemDetailPresenter {
    fun insItemDetail(param_item_name:String,param_item_price: String,paramItemColor: String , param_item_image: String,itemDetailActivity: ItemDetailActivity)
}
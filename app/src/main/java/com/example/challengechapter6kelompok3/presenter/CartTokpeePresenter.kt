package com.example.challengechapter6kelompok3.presenter

import com.example.challengechapter6kelompok3.CartTokpeeActivity
import com.example.challengechapter6kelompok3.databinding.ActivityCartTokpeeBinding

interface CartTokpeePresenter {
    fun getTotalItem(bindingCart : ActivityCartTokpeeBinding, cartTokpeeActivity: CartTokpeeActivity)
//    fun fetchData(cartTokpeeActivity: CartTokpeeActivity, bindingCart: ActivityCartTokpeeBinding)
}
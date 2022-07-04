package com.example.challengechapter6kelompok3.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter6kelompok3.databinding.CardItemCartBinding
import com.example.challengechapter6kelompok3.model.DataCart
import java.text.DecimalFormat
import java.text.NumberFormat

fun concatString(s1: String, s2: String): String {
    return s1 + s2
}

class CartItemAdapter(private val listItem: ArrayList<DataCart>, val onItemStoreClick: (DataCart) -> Unit) : RecyclerView.Adapter<CartItemAdapter.ViewHolderCart>() {


        class ViewHolderCart(private val dataCartBinding : CardItemCartBinding) : RecyclerView.ViewHolder(dataCartBinding.root) {
            fun bind(dataCart : DataCart) {

                //step 4 implement recyclerview, bind data ke view
                dataCartBinding.tvItemDesc.text = dataCart.itemDesc
                val formatter: NumberFormat = DecimalFormat("#,###")
                val formattedNumber: String = formatter.format(dataCart.itemPrice)
                val result = concat("Rp ",formattedNumber)
                dataCartBinding.tvItemPrice.text = result
                Glide.with(itemView.context).load(dataCart.itemImage).into(dataCartBinding.ivCover)
                dataCartBinding.clCartItem.setBackgroundColor(Color.parseColor(dataCart.itemColor));
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {
            val view = CardItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolderCart(view)
        }

        override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {
            val item : DataCart = listItem[position]

//            val descString = item.itemDesc
//            val priceInt = item.itemPrice
//            val formatter: NumberFormat = DecimalFormat("#,###")
//            val formattedNumber: String = formatter.format(item.itemPrice)
//            val result = concat("Rp ",formattedNumber)
//            val priceString = result
//            val imageString = item.itemImage
//            val colorString = item.itemColor
//
//            val toPass = Bundle()
//            toPass.putString("itemDesc", descString)
//            toPass.putInt("itemPrice", priceInt)
//            toPass.putString("itemPriceString", priceString)
//            toPass.putString("itemImage", imageString)
//            toPass.putString("itemColor", colorString)

            holder.itemView.setOnClickListener {
                onItemStoreClick(item)
            }
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return listItem.size
        }
    }

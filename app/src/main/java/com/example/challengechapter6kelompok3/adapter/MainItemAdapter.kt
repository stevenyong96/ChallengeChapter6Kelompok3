package com.example.challengechapter6kelompok3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengechapter6kelompok3.databinding.CardItemLinearBinding
import com.example.challengechapter6kelompok3.model.DataMain

class MainItemAdapter(private val listItem: ArrayList<DataMain>, val onItemStoreClick: (DataMain) -> Unit) : RecyclerView.Adapter<MainItemAdapter.ViewHolder>() {

        class ViewHolder(private val dataMainBinding : CardItemLinearBinding) : RecyclerView.ViewHolder(dataMainBinding.root) {
            fun bind(dataMain : DataMain) {

                //step 4 implement recyclerview, bind data ke view
                dataMainBinding.tvTitle.text = dataMain.itemDesc
                dataMainBinding.tvPrice.text = dataMain.itemPrice.toString()
                Glide.with(itemView.context).load(dataMain.itemImage).into(dataMainBinding.ivCover)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = CardItemLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item : DataMain = listItem[position]
            holder.itemView.setOnClickListener {
                onItemStoreClick(item)
            }
            holder.bind(item)
        }

        override fun getItemCount(): Int {
            return listItem.size
        }
    }

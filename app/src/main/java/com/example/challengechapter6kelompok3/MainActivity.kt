package com.example.challengechapter6kelompok3

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengechapter6kelompok3.adapter.MainItemAdapter
import com.example.challengechapter6kelompok3.databinding.ActivityMainBinding
import com.example.challengechapter6kelompok3.model.DataMain


class MainActivity : AppCompatActivity() {

    //step 2 untuk viewbinding, deklarasikan variable berdasarkan xml (setting gradle terlebih dahulu)
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // step 3 untuk viewbinding, bind xml ke variable
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //step 4 implement recyclerview, set adapter ke recyclerview dan set layoutmanager
        binding.rvListItem.adapter = MainItemAdapter(getDummyData()) {
            Toast.makeText(this, "Item di click : ${it.itemDesc}", Toast.LENGTH_SHORT).show()
        }
        binding.rvListItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }


    private fun getDummyData() : ArrayList<DataMain> {
        return arrayListOf(
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000,"#FF233D"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas NMD1", 2850000,"#F5CA7B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Nike", 300000,"#EEEE9B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Jacket", 950000,"#98fb98"),
        )
    }
}


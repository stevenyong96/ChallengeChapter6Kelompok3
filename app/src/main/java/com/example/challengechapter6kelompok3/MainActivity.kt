package com.example.challengechapter6kelompok3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.rvListItem.layoutManager = LinearLayoutManager(this)

    }

    private fun getDummyData() : ArrayList<DataMain> {
        return arrayListOf(
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000),
        )
    }
}


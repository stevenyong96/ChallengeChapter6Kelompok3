package com.example.challengechapter6kelompok3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter6kelompok3.adapter.MainItemAdapter
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.databinding.ActivityMainBinding
import com.example.challengechapter6kelompok3.databinding.ActivityMainNavBinding
import com.example.challengechapter6kelompok3.databinding.AppBarMainBinding
import com.example.challengechapter6kelompok3.databinding.FragmentMainContentBinding
import com.example.challengechapter6kelompok3.model.DataMain
import com.example.challengechapter6kelompok3.presenter.MainPresenterImp
import com.example.challengechapter6kelompok3.presenter.MainView
import com.example.challengechapter6kelompok3.viewModel.MainViewModel
import com.example.challengechapter6kelompok3.viewModel.MyViewModelFactory


class MainActivity : AppCompatActivity() , MainView {

    //step 2 untuk viewbinding, deklarasikan variable berdasarkan xml (setting gradle terlebih dahulu)
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding : FragmentMainContentBinding
    private lateinit var bindingMenu : ActivityMainBinding
    private lateinit var bindingNav : ActivityMainNavBinding
    private lateinit var bindingAppBarMain : AppBarMainBinding
//    private lateinit var viewModel: MainViewModel
    lateinit var presenter: MainPresenterImp

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // step 3 untuk viewbinding, bind xml ke variable
        bindingMenu = ActivityMainBinding.inflate(layoutInflater)
        binding = FragmentMainContentBinding.inflate(layoutInflater)
        bindingNav = ActivityMainNavBinding.inflate(layoutInflater)
        bindingAppBarMain = AppBarMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")
        presenter = MainPresenterImp(this)
        var tempData = presenter.getDummyData()

//        var drawerLayout = bindingMenu.drawerLayout
//        var toolBar = binding.ivNavMenuBar
//        var actionBarDrawerToggle = ActionBarDrawerToggle
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

//        //instance of database
//        val userDatabase = UserDatabase.getInstance(this)
//        val myViewModelFactory = MyViewModelFactory(userDatabase!!)
//
//        viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)

        binding.tvCustomer.setText(tempUsername)

        //step 4 implement recyclerview, set adapter ke recyclerview dan set layoutmanager
        binding.rvListItem.adapter = MainItemAdapter(tempData) {
            Toast.makeText(this, "Item di click : ${it.itemDesc}", Toast.LENGTH_SHORT).show()
            var intentToItemDetail= Intent(this,ItemDetailActivity::class.java)
            intentToItemDetail.putExtra("KEY_ITEM_NAME", it.itemDesc)
            intentToItemDetail.putExtra("KEY_ITEM_PRICE", it.itemPrice.toString())
            intentToItemDetail.putExtra("KEY_ITEM_COLOR", it.itemColor)
            intentToItemDetail.putExtra("KEY_ITEM_IMAGE", it.itemImage)
            intentToItemDetail.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToItemDetail)
        }
        binding.rvListItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.ivNavMenuBar.setOnClickListener {
            SharedPrefManager.setIsLandingPageShown(this,true)
            val intentToLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentToLogin)
        }

        binding.ivCartMain.setOnClickListener {
            val intentToCart = Intent(this,CartTokpeeActivity::class.java)
            intentToCart.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToCart)
        }

    }


    private fun getDummyData() : ArrayList<DataMain> {
        return arrayListOf(
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000,"#FF233D"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_nmd.png", "Adidas NMD1", 2850000,"#F5CA7B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/nike_jacket.png", "Nike Jacket", 300000,"#EEEE9B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/divo_backpack.png", "Divo Backpack", 950000,"#98fb98"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/sepatu_nike.png", "Sepatu Nike Kobe", 2500000,"#AF8FE9"),
        )
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showData(data: String) {
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle("Data")

        dialog.setMessage(data)

        dialog.show()
    }

    override fun clearField() {
        binding.searchView.setQuery("", false);
        binding.searchView.clearFocus();
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }


}


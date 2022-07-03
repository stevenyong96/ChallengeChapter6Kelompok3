package com.example.challengechapter6kelompok3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.challengechapter6kelompok3.model.DataSplash
import com.example.challengechapter6kelompok3.presenter.SplashPresenterImp
import com.example.challengechapter6kelompok3.presenter.SplashView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() , SplashView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val localVer = "1.0.0"
        val currentVer = "1.0.0" //get from api version later

        val presenter = SplashPresenterImp(this)
        presenter.checkVersion(localVer,currentVer)
        val isLandingPageShown = SharedPrefManager.getIsLandingPageShown(this)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            if(isLandingPageShown){
                startActivity(Intent(this@SplashActivity, LandingActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        }
    }

    override fun currentVersion(dataView : DataSplash){
        Log.d(SplashActivity::class.java.simpleName,dataView.dataVersion.toString())
    }

    override fun setCurrentVersion(dataView : DataSplash, paramCurrentVersion : String){
        dataView.dataVersion = paramCurrentVersion
        Log.d(SplashActivity::class.java.simpleName,"Version Updated to : Version " + dataView.dataVersion.toString())
    }

    override fun statusMessage(dataView : DataSplash){
        Log.d(SplashActivity::class.java.simpleName,dataView.dataMessage.toString())
    }
}
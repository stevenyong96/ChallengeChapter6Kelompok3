package com.example.challengechapter6kelompok3.presenter

import com.example.challengechapter6kelompok3.model.DataSplash

class SplashPresenster(val dataView: SplashView) {
    fun checkVersion(localVersion: String , currentVersion: String){
        val message = ""
        val model = DataSplash()
        if(localVersion === currentVersion){
            //Memanggil model Data Splash
            model.dataMessage = "UPTODATE VERSION"
            dataView.statusMessage(model)
        }
        else{
            model.dataMessage = "MISMATCH VERSION"
            dataView.statusMessage(model)
        }

    }

    fun getCurrentVersion(localVersion: String): String {
        return localVersion
    }
}
package com.example.challengechapter6kelompok3.presenter

import com.example.challengechapter6kelompok3.model.DataSplash

interface SplashView {
    fun currentVersion(data : DataSplash)
    fun setCurrentVersion(data : DataSplash,paramCurrentVersion : String)
    fun statusMessage(data: DataSplash)
}
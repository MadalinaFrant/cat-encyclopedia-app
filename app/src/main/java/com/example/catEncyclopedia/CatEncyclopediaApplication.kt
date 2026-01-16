package com.example.catEncyclopedia

import android.app.Application
import com.example.catEncyclopedia.data.AppContainer
import com.example.catEncyclopedia.data.AppDataContainer

class CatEncyclopediaApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}

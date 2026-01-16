package com.example.catEncyclopedia.data

import android.content.Context
import com.example.catEncyclopedia.network.CatApi

interface AppContainer {
    val catBreedRepository: CatBreedRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val catBreedRepository: CatBreedRepository by lazy {
        OfflineCatBreedRepository(CatEncyclopediaDatabase.getDatabase(context).catBreedDao(), CatApi.retrofitService)
    }
}

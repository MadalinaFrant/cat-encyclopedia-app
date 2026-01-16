package com.example.catEncyclopedia.data

import kotlinx.coroutines.flow.Flow

interface CatBreedRepository {
    fun getAllCatBreedsStream(): Flow<List<CatBreed>>

    fun getCatBreedStream(id: String): Flow<CatBreed?>

    suspend fun insertAllCatBreeds(catBreeds: List<CatBreed>)

    suspend fun insertCatBreed(catBreed: CatBreed)

    suspend fun deleteCatBreed(catBreed: CatBreed)

    suspend fun updateCatBreed(catBreed: CatBreed)

    suspend fun fetchCatBreedsFromApi(): List<CatBreed>

    suspend fun fetchCatImageByIdFromApi(imageId: String): CatImage
}


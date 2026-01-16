package com.example.catEncyclopedia.data

import com.example.catEncyclopedia.network.CatApiService
import kotlinx.coroutines.flow.Flow

class OfflineCatBreedRepository(private val catBreedDao: CatBreedDao, private val catApiService: CatApiService) : CatBreedRepository {
    override fun getAllCatBreedsStream(): Flow<List<CatBreed>> = catBreedDao.getAllCatBreeds()

    override fun getCatBreedStream(id: String): Flow<CatBreed?> = catBreedDao.getCatBreed(id)

    override suspend fun insertAllCatBreeds(catBreeds: List<CatBreed>) = catBreedDao.insertAll(catBreeds)

    override suspend fun insertCatBreed(catBreed: CatBreed) = catBreedDao.insert(catBreed)

    override suspend fun deleteCatBreed(catBreed: CatBreed) = catBreedDao.delete(catBreed)

    override suspend fun updateCatBreed(catBreed: CatBreed) = catBreedDao.update(catBreed)

    override suspend fun fetchCatBreedsFromApi(): List<CatBreed> = catApiService.getCatBreeds()

    override suspend fun fetchCatImageByIdFromApi(imageId: String): CatImage = catApiService.getCatImageById(imageId)
}

package com.example.catEncyclopedia.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(catBreed: CatBreed)

    @Update
    suspend fun update(catBreed: CatBreed)

    @Delete
    suspend fun delete(catBreed: CatBreed)

    @Query("SELECT * from cat_breeds WHERE id = :id")
    fun getCatBreed(id: String): Flow<CatBreed>

    @Query("SELECT * from cat_breeds ORDER BY name ASC")
    fun getAllCatBreeds(): Flow<List<CatBreed>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(breeds: List<CatBreed>)
}

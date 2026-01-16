package com.example.catEncyclopedia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CatBreed::class], version = 1, exportSchema = false)
abstract class CatEncyclopediaDatabase : RoomDatabase() {

    abstract fun catBreedDao(): CatBreedDao

    companion object {
        @Volatile
        private var Instance: CatEncyclopediaDatabase? = null

        fun getDatabase(context: Context): CatEncyclopediaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CatEncyclopediaDatabase::class.java, "cat_encyclopedia_database")
                    .build().also { Instance = it }
            }
        }
    }
}


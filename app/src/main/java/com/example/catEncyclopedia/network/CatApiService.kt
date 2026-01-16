package com.example.catEncyclopedia.network

import com.example.catEncyclopedia.data.CatBreed
import com.example.catEncyclopedia.data.CatImage
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val BASE_URL = "https://api.thecatapi.com/v1/"

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface CatApiService {
    @Headers("x-api-key: live_gQvChwYgK3JqyDfc0qoCEnsDi4EMZEIHDaKmCVJuYd4i8Ip75jbGzUgweshcX8k5")
    @GET("breeds")
    suspend fun getCatBreeds(): List<CatBreed>

    @Headers("x-api-key: live_gQvChwYgK3JqyDfc0qoCEnsDi4EMZEIHDaKmCVJuYd4i8Ip75jbGzUgweshcX8k5")
    @GET("images/{image_id}")
    suspend fun getCatImageById(@Path("image_id") imageId: String): CatImage
}

object CatApi {
    val retrofitService: CatApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }
}

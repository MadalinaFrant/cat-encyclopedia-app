package com.example.catEncyclopedia.data

import kotlinx.serialization.Serializable

@Serializable
data class CatImage(
    val id: String,
    val url: String
)

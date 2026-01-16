package com.example.catEncyclopedia.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "cat_breeds")
data class CatBreed(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val origin: String,
    val temperament: String,
    @SerialName("life_span")
    val lifeSpan: String,
    @SerialName("reference_image_id")
    val referenceImageId: String? = null,
    val referenceImageUrl: String? = null
)

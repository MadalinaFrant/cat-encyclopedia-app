package com.example.catEncyclopedia.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catEncyclopedia.CatEncyclopediaApplication
import com.example.catEncyclopedia.data.CatBreed
import com.example.catEncyclopedia.data.CatBreedRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(private val catBreedRepository: CatBreedRepository) : ViewModel() {
    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        getCatBreeds()
    }

    fun getCatBreeds() {
        viewModelScope.launch {
            homeUiState = homeUiState.copy(loading = true)

            if (catBreedRepository.getAllCatBreedsStream().first().isEmpty()) {
                val catBreedsFromApi = catBreedRepository.fetchCatBreedsFromApi()
                val catBreedsWithImages = catBreedsFromApi.map { breed ->
                    if (!breed.referenceImageId.isNullOrEmpty()) {
                        val catImage = catBreedRepository.fetchCatImageByIdFromApi(breed.referenceImageId)
                        breed.copy(referenceImageUrl = catImage.url)
                    } else {
                        breed
                    }
                }
                catBreedRepository.insertAllCatBreeds(catBreedsWithImages)
            }
            homeUiState = HomeUiState(catBreedList = catBreedRepository.getAllCatBreedsStream().first(), loading = false)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CatEncyclopediaApplication)
                val catBreedRepository = application.container.catBreedRepository
                HomeViewModel(catBreedRepository)
            }
        }
    }
}

data class HomeUiState(
    val catBreedList: List<CatBreed> = listOf(),
    val loading: Boolean = false
)

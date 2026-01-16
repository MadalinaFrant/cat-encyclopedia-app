package com.example.catEncyclopedia.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catEncyclopedia.CatEncyclopediaApplication
import com.example.catEncyclopedia.data.CatBreed
import com.example.catEncyclopedia.data.CatBreedRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CatBreedDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val catBreedRepository: CatBreedRepository
) : ViewModel() {
    private val catBreedId: String = checkNotNull(savedStateHandle[CatBreedDetailsDestination.catBreedIdArg])

    var catBreedDetailsUiState by mutableStateOf(CatBreedDetailsUiState())
        private set

    init {
        getCatBreed()
    }

    private fun getCatBreed() {
        viewModelScope.launch {
            val breed = catBreedRepository.getCatBreedStream(catBreedId).first() ?: CatBreed("" ,"", "", "", "", "")
            catBreedDetailsUiState = CatBreedDetailsUiState(breed)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CatEncyclopediaApplication)
                val catBreedRepository = application.container.catBreedRepository
                CatBreedDetailsViewModel(
                    this.createSavedStateHandle(),
                    catBreedRepository
                )
            }
        }
    }
}

data class CatBreedDetailsUiState(
    val catBreed: CatBreed = CatBreed("" ,"", "", "", "", "")
)

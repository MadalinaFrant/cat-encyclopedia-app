package com.example.catEncyclopedia.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.catEncyclopedia.CatEncyclopediaApplication
import com.example.catEncyclopedia.ui.home.HomeViewModel
import com.example.catEncyclopedia.ui.details.CatBreedDetailsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            CatBreedDetailsViewModel(
                this.createSavedStateHandle(),
                catEncyclopediaApplication().container.catBreedRepository
            )
        }

        initializer {
            HomeViewModel(catEncyclopediaApplication().container.catBreedRepository)
        }
    }
}

fun CreationExtras.catEncyclopediaApplication(): CatEncyclopediaApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CatEncyclopediaApplication)

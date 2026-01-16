package com.example.catEncyclopedia.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.catEncyclopedia.ui.home.HomeDestination
import com.example.catEncyclopedia.ui.home.HomeScreen
import com.example.catEncyclopedia.ui.home.HomeViewModel
import com.example.catEncyclopedia.ui.details.CatBreedDetailsDestination
import com.example.catEncyclopedia.ui.details.CatBreedDetailsScreen
import com.example.catEncyclopedia.ui.details.CatBreedDetailsViewModel

@Composable
fun CatEncyclopediaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
            HomeScreen(
                homeUiState = homeViewModel.homeUiState,
                navigateToCatBreedDetails = {
                    navController.navigate("${CatBreedDetailsDestination.route}/${it}")
                })
        }
        composable(
            route = CatBreedDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(CatBreedDetailsDestination.catBreedIdArg) {
                type = NavType.StringType
            })
        ) {
            val catBreedDetailsViewModel: CatBreedDetailsViewModel = viewModel(factory = CatBreedDetailsViewModel.Factory)
            CatBreedDetailsScreen(
                catBreedDetailsUiState = catBreedDetailsViewModel.catBreedDetailsUiState,
                navigateBack = { navController.navigateUp() })
        }
    }
}

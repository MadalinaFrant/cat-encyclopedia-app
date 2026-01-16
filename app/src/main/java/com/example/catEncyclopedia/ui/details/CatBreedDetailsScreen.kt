package com.example.catEncyclopedia.ui.details

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.catEncyclopedia.CatEncyclopediaTopAppBar
import com.example.catEncyclopedia.R
import com.example.catEncyclopedia.data.CatBreed
import com.example.catEncyclopedia.ui.navigation.NavigationDestination
import com.example.catEncyclopedia.ui.theme.CatEncyclopediaTheme

object CatBreedDetailsDestination : NavigationDestination {
    override val route = "cat_breed_details"
    override val titleRes = R.string.cat_breed_detail_title
    const val catBreedIdArg = "catBreedId"
    val routeWithArgs = "$route/{$catBreedIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedDetailsScreen(
    catBreedDetailsUiState: CatBreedDetailsUiState,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CatEncyclopediaTopAppBar(
                title = stringResource(CatBreedDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
    ) { innerPadding ->
        CatBreedDetailsBody(
            catBreedDetailsUiState = catBreedDetailsUiState,
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun CatBreedDetailsBody(
    catBreedDetailsUiState: CatBreedDetailsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        CatBreedDetails(
            catBreed = catBreedDetailsUiState.catBreed,
            imageUrl = catBreedDetailsUiState.catBreed.referenceImageUrl,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CatBreedDetails(
    catBreed: CatBreed, modifier: Modifier = Modifier,
    imageUrl: String?
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            if (imageUrl != null) {
                AsyncImage(model = imageUrl, contentDescription = catBreed.name)
            }
            CatBreedDetailsSection(
                labelResID = R.string.cat_breed_name,
                catBreedDetail = catBreed.name,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            CatBreedDetailsSection(
                labelResID = R.string.cat_breed_description,
                catBreedDetail = catBreed.description,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            CatBreedDetailsSection(
                labelResID = R.string.cat_breed_origin,
                catBreedDetail = catBreed.origin,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            CatBreedDetailsSection(
                labelResID = R.string.cat_breed_temperament,
                catBreedDetail = catBreed.temperament,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
            CatBreedDetailsSection(
                labelResID = R.string.cat_breed_lifespan,
                catBreedDetail = catBreed.lifeSpan,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen
                            .padding_medium
                    )
                )
            )
        }

    }
}

@Composable
private fun CatBreedDetailsSection(
    @StringRes labelResID: Int, catBreedDetail: String, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = stringResource(labelResID), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = catBreedDetail)
    }
}

@Preview(showBackground = true)
@Composable
fun CatBreedDetailsScreenPreview() {
    CatEncyclopediaTheme {
        CatBreedDetailsBody(
            CatBreedDetailsUiState(
                catBreed = CatBreed("1", "test", "test", "test", "test", "test")
            )
        )
    }
}

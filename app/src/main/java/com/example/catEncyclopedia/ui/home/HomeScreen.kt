package com.example.catEncyclopedia.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.catEncyclopedia.CatEncyclopediaTopAppBar
import com.example.catEncyclopedia.R
import com.example.catEncyclopedia.data.CatBreed
import com.example.catEncyclopedia.ui.navigation.NavigationDestination
import com.example.catEncyclopedia.ui.theme.CatEncyclopediaTheme

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    navigateToCatBreedDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CatEncyclopediaTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        HomeBody(
            catBreedList = homeUiState.catBreedList,
            isLoading = homeUiState.loading,
            onCatBreedClick = navigateToCatBreedDetails,
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding,
        )
    }
}

@Composable
private fun HomeBody(
    catBreedList: List<CatBreed>,
    isLoading: Boolean,
    onCatBreedClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (isLoading) {
            Text(
                text = stringResource(R.string.loading_breeds_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else if (catBreedList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_breeds_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {
            CatEncyclopediaList(
                catBreedList = catBreedList,
                onCatBreedClick = { onCatBreedClick(it.id) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun CatEncyclopediaList(
    catBreedList: List<CatBreed>,
    onCatBreedClick: (CatBreed) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = catBreedList, key = { it.id }) { catBreed ->
            CatEncyclopediaCatBreed(catBreed = catBreed,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onCatBreedClick(catBreed) })
        }
    }
}

@Composable
private fun CatEncyclopediaCatBreed(
    catBreed: CatBreed, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = catBreed.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                if (!catBreed.referenceImageUrl.isNullOrEmpty())  {
                    AsyncImage(
                        model = catBreed.referenceImageUrl,
                        contentDescription = catBreed.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp))

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    CatEncyclopediaTheme {
        HomeBody(listOf(
            CatBreed("1", "test1", "test", "test", "test", "1", "test1"), CatBreed("2", "test2","test", "test", "test", "2", "test2"), CatBreed("3", "test3", "test", "test", "test", "3", "test3")
        ), isLoading = false, onCatBreedClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    CatEncyclopediaTheme {
        HomeBody(listOf(), isLoading = false, onCatBreedClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CatEncyclopediaCatBreedPreview() {
    CatEncyclopediaTheme {
        CatEncyclopediaCatBreed(
            CatBreed("1", "test", "test", "test", "test", "2", "1234"),
        )
    }
}

package com.gyurme.mycity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.gyurme.mycity.ui.MyCityViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.Recommendation
import com.gyurme.mycity.data.RecommendationDetail
import com.gyurme.mycity.data.local.LocalRecommendationDetailProvider.getDetailForRecommendation
import com.gyurme.mycity.ui.CategoryScreen
import com.gyurme.mycity.ui.RecommendationDetailScreen
import com.gyurme.mycity.ui.SelectRecommendationScreen

enum class MyCityScreen {
    Start,
    Recommendations,
    RecommendationDetail
}

/**
* Composable that displays the topBar and displays back button if back navigation is possible.
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topBarTitle =
    if (title.isEmpty()) {
        stringResource(R.string.app_name)
    } else {
        title
    }
    TopAppBar(
        title = { Text(topBarTitle) },
         colors = TopAppBarDefaults.mediumTopAppBarColors(
             containerColor = MaterialTheme.colorScheme.primaryContainer
         ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MyCityApp(modifier: Modifier = Modifier) {

    val viewModel: MyCityViewModel = viewModel()
    val myCityUiState = viewModel.uiState.collectAsState().value
    val navController: NavHostController = rememberNavController()

    Scaffold(
        topBar = {
            MyCityAppBar(
                title = myCityUiState.currentTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Start.name,
            modifier = Modifier.padding(innerPadding)

        ) {
            composable(route = MyCityScreen.Start.name) {
                CategoryScreen(
                    categories = myCityUiState.allRecommendations.keys.toList(),
                    onCategoryCardClicked = { category: Category ->
                        viewModel.setCategory(category)
                        navController.navigate(MyCityScreen.Recommendations.name)
                    },
                    modifier = modifier
                )
            }

            composable(route = MyCityScreen.Recommendations.name) {
                SelectRecommendationScreen(
                    recommendations = myCityUiState.currentRecommendations,
                    onRecommendationClicked = { recommendation ->
                        viewModel.setCurrentRecommendationDetail(recommendation)
                        navController.navigate(MyCityScreen.RecommendationDetail.name)
                    },
                    modifier = modifier
                )
            }

            composable(route = MyCityScreen.RecommendationDetail.name) {
               RecommendationDetailScreen(
                    recommendationDetail = myCityUiState.currentRecommendationDetail,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium)))
            }
        }
    }
}

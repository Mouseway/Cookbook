package com.example.cookbook2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.R
import com.example.cookbook2.screens.recipeDetail.InfoTab
import com.example.cookbook2.screens.recipeDetail.IngredientsTab
import com.example.cookbook2.screens.recipeDetail.ProcedureTab
import com.example.cookbook2.screens.recipeDetail.RecipeDetailViewModel
import com.example.cookbook2.utils.FavoriteState
import com.example.cookbook2.utils.MyAppBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@ExperimentalPagerApi
@Composable
fun RecipeDetailScreen(navController: NavHostController, recipeId: Int?){

    if(recipeId == null)
        throw IllegalArgumentException("recipe id is null")

    val viewModel by viewModel<RecipeDetailViewModel>{ parametersOf(recipeId)}

    val recipe = viewModel.recipe.observeAsState()

    val pagerState = rememberPagerState()

    Scaffold(
        topBar = {
            // TopBar with back button and favorite button
             MyAppBar(
                 backButton = true,
                 onBackBtnClick = {
                     navController.popBackStack()
                 },
                 favoriteButton = true,
                 onFavoriteClick = {
                    viewModel.swapFavorite()
                 },
                 favoriteState = recipe.value?.favorite ?: FavoriteState.NOT_FAVORITE
             )
        },
        bottomBar = {
            RecipeDetailTabs(pagerState)
        }
    ){ innerPadding ->
        // Tab content - InfoTab, IngredientsTab or ProcedureTab
        Box(
            Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)){
            recipe.value?.let { TabContent(pagerState, recipe = it) }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabContent(pagerState: PagerState, recipe: Recipe) {
    HorizontalPager(count = 3, state = pagerState) { page ->
        when(page){
            0 -> InfoTab(recipe = recipe)
            1 -> IngredientsTab(recipe = recipe)
            2 -> ProcedureTab(recipe = recipe)
        }
    }
}

// Bottom bar with buttons for change displayed recipe information ( Basic info, Ingredients, Steps)
@ExperimentalPagerApi
@Composable
fun RecipeDetailTabs(pagerState: PagerState) {

    val tabsList: List<Pair<String, Int>> = listOf(
        "Info" to R.drawable.info,
        "Ingredients" to R.drawable.ingredients,
        "Steps" to R.drawable.steps
    )

    val scope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            tabsList.forEachIndexed { index, pair ->
                Tab(selected = pagerState.currentPage == index,
                    icon = {
                        Image(painter = painterResource(id = pair.second), contentDescription = pair.first,
                            Modifier
                                .padding(5.dp)
                                .size(40.dp))
                    },
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                )
            }
    }
}

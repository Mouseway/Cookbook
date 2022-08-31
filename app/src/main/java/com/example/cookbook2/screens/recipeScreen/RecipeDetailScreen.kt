package com.example.cookbook2.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.R
import com.example.cookbook2.models.RecipesViewModel
import com.example.cookbook2.screens.recipeScreen.InfoTab
import com.example.cookbook2.screens.recipeScreen.IngredientsTab
import com.example.cookbook2.screens.recipeScreen.ProcedureTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel

@ExperimentalPagerApi
@Composable
fun RecipeDetailScreen(navController: NavHostController, recipeId: Int?){

    if(recipeId == null)
        throw IllegalArgumentException("recipe's id is null")

    val modelView by viewModel<RecipesViewModel>()
    val recipe: Recipe = modelView.getRecipeById(recipeId) ?: throw IllegalArgumentException()

    val pagerState = rememberPagerState()

    Scaffold(
        topBar = {
             RecipeDetailTopAppBar(){
                 navController.popBackStack()
             }
        },
        bottomBar = {
            Tabs(pagerState)
        }
    ){ innerPadding ->
        Box(Modifier.padding(innerPadding).background(MaterialTheme.colorScheme.background)){
            TabContent(pagerState, recipe = recipe)
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

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {

    val tabsList: List<Pair<String, Int>> = listOf(
        "Info" to R.drawable.info,
        "Ingredients" to R.drawable.ingredients,
        "Steps" to R.drawable.steps
    )

    val scope = rememberCoroutineScope()
//    BottomAppBar(modifier = Modifier.background(MaterialTheme.colorScheme.primary).padding(0.dp)) {
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
//    }
}

@Composable
fun RecipeDetailTopAppBar(onBackBtnClick: () -> Unit){
    TopAppBar(backgroundColor = MaterialTheme.colorScheme.primary) {
        Image(painter = painterResource(id = R.drawable.arrow_small_left),
            contentDescription = "Back",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
            modifier =  Modifier
                .padding(5.dp)
                .clickable { onBackBtnClick() }
        )
    }
}

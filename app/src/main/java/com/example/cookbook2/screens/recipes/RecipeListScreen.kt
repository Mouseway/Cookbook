package com.example.cookbook2.screens.recipes

import android.net.Uri
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.navigation.NavigationScreens
import com.example.cookbook2.utils.ASSETS_IMAGES_PATH
import com.example.cookbook2.utils.MyAppBar
import com.example.cookbook2.utils.SearchingTopBar
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RecipesListScreen(navController: NavHostController, categoryId: Int){

    val viewModel by viewModel<RecipesViewModel>(){ parametersOf(categoryId)}

    val items: List<Recipe>? by viewModel.recipes.observeAsState()
    val recipes = items ?: listOf<Recipe>()

    // Selected category
    val category = viewModel.category


    val searchedText = viewModel.searchedText
    // True if searching bar is shown
    val searchingMode = viewModel.searchingMode

    val focusManager = LocalFocusManager.current


    // True if focus on searchbar is requested
    val searchingBarFocus = remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(topBar = {
        Crossfade(targetState = searchingMode.value) { sMode ->
            if(!sMode){
                // TopBar with back button, title and search button
                MyAppBar(category.value.title,
                    backButton = true,
                    onBackBtnClick = {navController.popBackStack()},
                    searchButton = true,
                    onSearchClick = {
                        searchingBarFocus.value = true
                        searchingMode.value = true
                    }
                )
            } else{
                // TopBar with searching bar
                SearchingTopBar(
                    text = searchedText.value,
                    onTextChange = {
                        viewModel.onSearchedTextChange(it)
                    },
                    onCloseClick = {
                        searchingMode.value = false
                        searchingBarFocus.value = false
                    },
                    onSearchClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        searchingBarFocus.value = false
                    },
                    focusRequested = searchingBarFocus.value
                )
            }
        }
    }) { padding ->
        // List of recipes
        LazyColumn(contentPadding = padding){
            items(recipes.size){
                index ->
                    RecipePreview(
                        recipe = recipes[index],
                        onClick = {
                            navController.navigate(NavigationScreens.RecipeDetailScreen.route + "/${recipes[index].id}")
                        },
                        onFavoriteClick = {
                            viewModel.swapFavorite(recipes[index])
                        }
                )
            }
        }
    }

}

@Composable
fun RecipePreview(recipe: Recipe, onClick: (Int) -> Unit, onFavoriteClick: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(7.dp, shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onClick(recipe.id) }


    ){
        Box(
            Modifier
                .height(180.dp)
                .fillMaxWidth()){
            // Recipe image

            AsyncImage(
                model = Uri.parse( ASSETS_IMAGES_PATH + recipe.imageSrc),
                contentDescription = recipe.title + " image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Heart button
            IconButton(onClick = { onFavoriteClick() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = recipe.favorite.icon),
                    contentDescription = "favorite",
                    tint = Color.White
                )
            }
        }

        // Recipe title
        Text(
            text = recipe.title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 5.dp)
        )
    }
}

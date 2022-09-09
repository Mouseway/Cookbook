package com.example.cookbook2.screens.recipes

//import coil.compose.AsyncImage
//import coil.request.ImageRequest
import android.util.Log
import androidx.compose.animation.Crossfade
import com.example.cookbook2.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.navigation.NavigationScreens
import com.example.cookbook2.utils.AssetLoader
import com.example.cookbook2.utils.MyAppBar
import com.example.cookbook2.utils.SearchingTopBar
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import java.io.File
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipesListScreen(navController: NavHostController, categoryId: Int){

    val viewModel by viewModel<RecipesViewModel>(){ parametersOf(categoryId)}

    val items: List<Recipe>? by viewModel.recipes.observeAsState()
    val recipes = items ?: listOf<Recipe>()


    val category = viewModel.category
    val searchedText = viewModel.searchedText

    val searchingMode = viewModel.searchingMode

    Scaffold(topBar = {
        Crossfade(targetState = searchingMode.value) { mode ->
            if(mode){
                SearchingTopBar(text = searchedText.value, onTextChange = {
                    viewModel.onSearchedTextChange(it)
                },
                    onCloseClick = {
                        searchingMode.value = false
                    })
            } else{
                MyAppBar(category.value.title,
                    backButton = true,
                    onBackBtnClick = {navController.popBackStack()},
                    searchButton = true,
                    onSearchClick = { searchingMode.value = true}
                )
            }
        }
    }) { padding ->

        LazyColumn(contentPadding = padding){
            items(recipes.size){
                    index ->
                RecipePreview(recipe = recipes[index]){
                    navController.navigate(NavigationScreens.RecipeDetailScreen.route + "/${recipes[index].id}")
                }
            }
        }
    }

}

@Composable
fun RecipePreview(recipe: Recipe, onClick: (Int) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(7.dp, shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable { onClick(recipe.id) }


    ){
        Image(
            painter = rememberDrawablePainter(drawable = AssetLoader.loadImage(recipe.imageSrc)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Text(
            text = recipe.title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 20.dp, top = 5.dp, bottom = 5.dp, end = 5.dp)
        )
    }
}

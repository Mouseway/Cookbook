package com.example.cookbook2.screens.listScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.models.RecipesViewModel
import com.example.cookbook2.navigation.NavigationScreens
import org.koin.androidx.compose.viewModel
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RecipesListScreen(navController: NavHostController){

    val viewModel by viewModel<RecipesViewModel>()

    val items: List<Recipe>? by viewModel.recipes.observeAsState()
    val recipes = items ?: listOf<Recipe>()


    Scaffold(topBar = {
        TopAppBar(backgroundColor = MaterialTheme.colorScheme.primary, modifier = Modifier.shadow(0.dp), elevation = 0.dp) {
            Text(
                text = "Recepty",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }) { padding ->
        LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = padding){
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
            painter = painterResource(id = recipe.imageSrc),
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
        )
        Text(
            text = recipe.title,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(5.dp)
        )
    }
}

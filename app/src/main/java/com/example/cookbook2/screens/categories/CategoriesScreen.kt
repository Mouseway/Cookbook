package com.example.cookbook2.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cookbook2.domain.Category
import com.example.cookbook2.navigation.NavigationScreens
import com.example.cookbook2.utils.AssetLoader
import com.example.cookbook2.utils.MyAppBar
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import org.koin.androidx.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(navController: NavHostController){

    val modelView by viewModel<CategoriesViewModel>()
    val items: List<Category>? by modelView.categories.observeAsState()
    val categories: List<Category> = listOf(Category.all, Category.favorite, Category.fast) + (items ?: listOf()) + listOf(Category.others)

    Scaffold(
        topBar = { MyAppBar(title = "Kategorie") }
    ) { padding ->
        // List of categories
        Box(Modifier.padding(padding)){
            LazyVerticalGrid(GridCells.Fixed(2), contentPadding = PaddingValues(bottom = 15.dp)) {
                items(categories.size){ index ->
                    CategoryView(category = categories[index]){
                        navController.navigate(NavigationScreens.RecipesListScreen.route + "/${categories[index].id}")
                    }
                }
            }
        }
    }
}


@Composable
fun CategoryView(category: Category, onClick: () -> Unit){
    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .clickable { onClick() }

    ){
        // Category image
        Box(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()){
            Image(
                painter = rememberDrawablePainter(drawable = AssetLoader.loadImage(category.imageSrc)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.7F)
                    .wrapContentHeight()
                    .align(Alignment.TopCenter)
            )

        }
        // Category title
        Box(modifier = Modifier
            .fillMaxWidth()){
            Text(
                text = category.title,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .align(Alignment.TopCenter),
                textAlign = TextAlign.Center
            )

        }
    }
}

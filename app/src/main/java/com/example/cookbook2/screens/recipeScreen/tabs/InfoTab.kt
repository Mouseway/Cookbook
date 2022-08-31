package com.example.cookbook2.screens.recipeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook2.R
import com.example.cookbook2.domain.Recipe

@Composable
fun InfoTab(recipe: Recipe){
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
        Image(painter =
        painterResource(id = recipe.imageSrc),
            contentDescription = recipe.title + " image",
            modifier = Modifier.fillMaxWidth()
        )
        RecipeTitle(title = recipe.title)
        Divider(Modifier.padding(5.dp))
        RecipeProperties(recipe.time, recipe.difficulty, recipe.servings)
        Text(text = recipe.description,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp))
    }
}


@Composable
fun RecipeTitle(title: String){
    Text(text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun RecipeProperties(time: Int, difficulty: Double, servings: Int){
    Row(){
        RecipeProperty(R.drawable.time, value = "$time min", description = "time")
        Spacer(modifier = Modifier.weight(1F))
        RecipeProperty(R.drawable.difficulty, value = difficulty.toString(), description = "difficulty")
        Spacer(modifier = Modifier.weight(1F))
        RecipeProperty(R.drawable.servings, value = servings.toString(), description = "servings")
    }
}

@Composable
fun RecipeProperty(icon: Int, value: String, description: String){
    Row(){
        Image(painter = painterResource(id = icon), contentDescription = description, Modifier.size(30.dp))
        Text(text = value, fontSize = 20.sp, modifier = Modifier.padding(start = 8.dp, top = 3.dp))
    }
}
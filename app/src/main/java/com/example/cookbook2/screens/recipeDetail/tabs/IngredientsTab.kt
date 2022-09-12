package com.example.cookbook2.screens.recipeDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.R

@Composable
fun IngredientsTab(recipe: Recipe){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
        Column(Modifier.align(Alignment.TopCenter)) {
            // Title
            Text(text = "Suroviny",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground)

            Divider(Modifier.padding(5.dp))
            // List of ingredients
            Text(
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 10.dp),
                text = buildAnnotatedString {
                    recipe.ingredients.forEach { ingredient ->
                        withStyle(style = ParagraphStyle(lineHeight = 25.sp)) {
                            // bullet list
                            append( "\u2022")
                            append("\t\t")
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                                append(ingredient.amount)
                            }
                            append(" ")
                            append(ingredient.foodstuff)
                        }
                    }
                }
            )
        }
        // Shows image but only if there the few ingredients
        if(recipe.ingredients.size < 10){
            Box(Modifier.align(Alignment.BottomCenter).padding(start = 20.dp, end = 20.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ingredients_list),
                    contentDescription = "Ingredients"
                )
            }
        }
    }
}
package com.example.cookbook2

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cookbook2.data.RecipesResource
import com.example.cookbook2.screens.RecipeScreen
import com.example.cookbook2.ui.theme.Cookbook2Theme
import com.google.accompanist.pager.ExperimentalPagerApi

//import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cookbook2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeScreen(recipe = RecipesResource.getById(1)!!)
                }
            }
        }
    }
}


//@OptIn(ExperimentalPagerApi::class)
@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Cookbook2Theme {
        RecipeScreen(recipe = RecipesResource.getById(1)!!)
    }
}
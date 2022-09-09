package com.example.cookbook2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cookbook2.screens.AddRecipeScreen
import com.example.cookbook2.screens.RecipeDetailScreen
import com.example.cookbook2.screens.categories.CategoriesScreen
import com.example.cookbook2.screens.recipes.RecipesListScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationScreens.CategoriesScreen.route) {
        composable(
            route = NavigationScreens.RecipesListScreen.route + "/{categoryId}",
            arguments = listOf(navArgument(name = "categoryId"){
                type = NavType.IntType
            })
        ) { entry ->
            entry.arguments?.getInt("categoryId")
                ?.let { RecipesListScreen(navController = navController, it) }
        }
        composable(
            route = NavigationScreens.RecipeDetailScreen.route + "/{recipeId}",
            arguments = listOf(navArgument(name = "recipeId") {
                type = NavType.IntType
            })
        ) { entry ->
            RecipeDetailScreen(navController, entry.arguments?.getInt("recipeId"))
        }
        composable(
            route = NavigationScreens.AddRecipeScreen.route,
        ){
            AddRecipeScreen(navController = navController)
        }
        composable(
            route = NavigationScreens.CategoriesScreen.route
        ){
            CategoriesScreen(navController = navController)
        }
    }

}
package com.example.cookbook2.navigation

sealed class NavigationScreens(val route: String) {
    object RecipesListScreen : NavigationScreens("recipe_list_screen")
    object RecipeDetailScreen : NavigationScreens("recipe_detail_screen")
}

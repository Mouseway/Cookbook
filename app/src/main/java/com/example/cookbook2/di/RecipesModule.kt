package com.example.cookbook2.di

import com.example.cookbook2.data.room.CookbookDatabase
import com.example.cookbook2.data.repositories.RecipesRepository
import com.example.cookbook2.screens.recipeDetail.RecipeDetailViewModel
import com.example.cookbook2.screens.recipes.RecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipesModule = module {

    factory {
        get<CookbookDatabase>().recipeDao
    }
    single {
        RecipesRepository(dao = get())
    }
    viewModel { params ->
        RecipesViewModel(categoryId = params.get() ,recipesRepository = get(), categoriesRepository = get())
    }
    viewModel { params ->
        RecipeDetailViewModel(recipeId = params.get(), recipesRepository = get())
    }
}
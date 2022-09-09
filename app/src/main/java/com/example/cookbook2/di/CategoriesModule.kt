package com.example.cookbook2.di

import com.example.cookbook2.data.repositories.CategoriesRepository
import com.example.cookbook2.data.room.CookbookDatabase
import com.example.cookbook2.screens.categories.CategoriesViewModel
import com.example.cookbook2.screens.recipeDetail.RecipeDetailViewModel
import com.example.cookbook2.screens.recipes.RecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val categoriesModule =  module {
    factory {
        get<CookbookDatabase>().categoryDao
    }

    single {
        CategoriesRepository(dao = get())
    }
    viewModel {
        CategoriesViewModel(categoriesRepository = get())
    }
}
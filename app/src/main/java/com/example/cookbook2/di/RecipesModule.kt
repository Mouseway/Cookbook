package com.example.cookbook2.di

import com.example.cookbook2.models.RecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipesModule = module {
    viewModel {
        RecipesViewModel()
    }
}
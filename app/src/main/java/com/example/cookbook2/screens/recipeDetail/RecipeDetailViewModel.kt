package com.example.cookbook2.screens.recipeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook2.data.repositories.RecipesRepository
import com.example.cookbook2.di.recipesModule
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.utils.FavoriteState
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val recipeId: Int, private val recipesRepository: RecipesRepository) : ViewModel() {

    private lateinit var _recipe: LiveData<Recipe>
    val recipe: LiveData<Recipe>
        get() = _recipe

    init {
        viewModelScope.launch{
            _recipe = recipesRepository.getRecipeById(recipeId)
        }
    }

    fun swapFavorite(){
        val swapped = recipe.value?.swappedFavorite() ?: throw NullPointerException("Null can't be so much popular!")
        viewModelScope.launch{
            recipesRepository.updateRecipeInfo(recipe = swapped)
        }
    }
}
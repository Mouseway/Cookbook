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

    lateinit private var _recipe: LiveData<Recipe>
    val recipe: LiveData<Recipe>
        get() = _recipe

    init {
        viewModelScope.launch{
            _recipe = recipesRepository.getRecipeById(recipeId)
        }
    }

    fun swapFavorite(){
        val newState = if(recipe.value?.favorite == FavoriteState.FAVORITE){
             FavoriteState.NOT_FAVORITE
        }else{
            FavoriteState.FAVORITE
        }

        val copy = recipe.value?.copy(favorite = newState) ?: throw NullPointerException("Null can't be so much popular!")

        viewModelScope.launch{
            recipesRepository.updateRecipeInfo(recipe = copy)
        }

    }
}
package com.example.cookbook2.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook2.data.RecipesResource
import com.example.cookbook2.domain.Recipe
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel(){

    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = _recipes

    init {
        viewModelScope.launch {
            _recipes.value = RecipesResource.getAllRecipes()
        }
    }

    fun getRecipeById(id: Int): Recipe? {
        return RecipesResource.getById(id)
    }
}
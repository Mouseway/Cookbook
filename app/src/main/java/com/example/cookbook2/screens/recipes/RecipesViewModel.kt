package com.example.cookbook2.screens.recipes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook2.data.repositories.CategoriesRepository
import com.example.cookbook2.data.repositories.RecipesRepository
import com.example.cookbook2.domain.Category
import com.example.cookbook2.domain.Recipe
import kotlinx.coroutines.launch

class RecipesViewModel(private val categoryId: Int, val recipesRepository: RecipesRepository, categoriesRepository: CategoriesRepository) : ViewModel(){

    // All recipes in selected category
    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()
    // Recipes filtered by searched text
    private val filteredRecipes: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>>
        get() = filteredRecipes


    val searchingMode = mutableStateOf(false)
    private val _searchedText = mutableStateOf("")
    val searchedText: State<String>
        get() = _searchedText

    val category: MutableState<Category> = mutableStateOf(Category.default)

    init {
        viewModelScope.launch {

            // Loading selected category from repository
            category.value = categoriesRepository.getCategoryById(categoryId)

            // Getting flow with all recipes in selected category
            recipesRepository.getByCategory(categoryId).collect{
                _recipes.value = it
                filterRecipes()
            }

        }
    }

    fun onSearchedTextChange(newText: String){
        _searchedText.value = newText
        filterRecipes()
    }

    private fun filterRecipes(){
        val text = _searchedText.value
        if(text == ""){
            filteredRecipes.value = _recipes.value
        }else{
            filteredRecipes.value = _recipes.value?.filter { recipe ->
                recipe.titleContains(text)
            }
        }
    }

    fun swapFavorite(recipe: Recipe){
        viewModelScope.launch {
            recipesRepository.updateRecipeInfo(recipe.swappedFavorite())
        }
    }
}
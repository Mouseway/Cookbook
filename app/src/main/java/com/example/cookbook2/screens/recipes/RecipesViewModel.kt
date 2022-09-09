package com.example.cookbook2.screens.recipes

import android.content.res.Resources
import android.graphics.BitmapFactory
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook2.App
import com.example.cookbook2.R
import com.example.cookbook2.data.repositories.CategoriesRepository
import com.example.cookbook2.data.repositories.RecipesRepository
import com.example.cookbook2.domain.Category
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.utils.unaccent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesViewModel(private val categoryId: Int, val recipesRepository: RecipesRepository, categoriesRepository: CategoriesRepository) : ViewModel(){

    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()
    private val filteredRecipes: MutableLiveData<List<Recipe>> = MutableLiveData<List<Recipe>>()

    val searchingMode = mutableStateOf(false)

    val recipes: LiveData<List<Recipe>>
        get() = filteredRecipes



    private val _searchedText = mutableStateOf("")
    val searchedText: State<String>
        get() = _searchedText

    lateinit var recipe: MutableState<Recipe>

    val category: MutableState<Category> = mutableStateOf(Category.default)

    init {
        viewModelScope.launch {

            category.value = when(categoryId){
                Category.all.id -> Category.all
                else -> categoriesRepository.getCategoryById(categoryId)
            }

            val recipesFlow = when(categoryId){
                Category.all.id -> recipesRepository.getAllRecipes()
                else -> recipesRepository.getByCategory(categoryId)
            }

            recipesFlow.collect{
                _recipes.value = it
                filteredRecipes.value = it
            }
        }
    }

    fun onSearchedTextChange(newText: String){
        _searchedText.value = newText
        if(newText == ""){
            filteredRecipes.value = _recipes.value
        }else{
            filteredRecipes.value = _recipes.value?.filter { recipe ->
                recipe.titleContains(newText)
            }
//                ?.sortedBy { if(it.title.lowercase().contains(newText.lowercase())) 0 else 1 }
        }
    }
}
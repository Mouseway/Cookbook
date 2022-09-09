package com.example.cookbook2.screens.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook2.data.repositories.CategoriesRepository
import com.example.cookbook2.domain.Category
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoriesViewModel(categoriesRepository: CategoriesRepository) : ViewModel() {

    private var _categories: MutableLiveData<List<Category>> = MutableLiveData<List<Category>>()

    val categories: LiveData<List<Category>>
        get() = _categories

    init {

        viewModelScope.launch {
            categoriesRepository.getAllCategories().collect{
                _categories.value = it
            }
        }

    }

}
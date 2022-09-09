package com.example.cookbook2.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cookbook2.utils.FavoriteState
import com.example.cookbook2.utils.unaccent

data class Recipe(
    val id: Int = 0,
    val title: String,
    val imageSrc: String,
    val description: String,
    val ingredients: List<IngredientItem>,
    val steps: List<String>,
    val time: Int,
    val difficulty: Double,
    val servings: Int,
    val favorite: FavoriteState
) {
    fun titleContains(text: String): Boolean{
       return title.lowercase().unaccent().contains(text.lowercase().unaccent())
    }

}

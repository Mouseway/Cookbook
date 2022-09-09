package com.example.cookbook2.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int,
    val amount: String,
    val foodstuff: String
) {
}
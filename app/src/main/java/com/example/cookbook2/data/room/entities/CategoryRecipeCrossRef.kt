package com.example.cookbook2.data.room.entities

import androidx.room.Entity

@Entity(primaryKeys = ["categoryId", "recipeId"])
data class CategoryRecipeCrossRef(
    val categoryId: Int,
    val recipeId: Int
) {
}
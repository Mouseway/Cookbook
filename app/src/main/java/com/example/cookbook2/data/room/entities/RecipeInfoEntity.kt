package com.example.cookbook2.data.room.entities

import androidx.room.*

@Entity(tableName = "recipe")
data class RecipeInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val imageSrc: String,
    val description: String,
    val time: Int,
    val difficulty: Double,
    val servings: Int,
    val steps: String,
    val favorite: Boolean
) {}

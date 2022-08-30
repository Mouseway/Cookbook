package com.example.cookbook2.data

data class Recipe(
    val id: Int,
    val title: String,
    val imageSrc: Int,
    val description: String,
    val ingredients: List<Pair<String, String>>,
    val steps: List<String>,
    val time: Int,
    val difficulty: Double,
    val servings: Int
) {}

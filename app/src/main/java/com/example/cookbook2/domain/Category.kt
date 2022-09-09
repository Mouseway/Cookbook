package com.example.cookbook2.domain

import com.example.cookbook2.R

class Category(
    val id: Int = 0,
    val imageSrc: String,
    val title: String
) {
    companion object {

        private const val defaultImagePath = "categories/cookbook.png"

        const val FAST_RECIPE_MAX_TIME = 30

        val all = Category(
            id = -1,
            imageSrc = defaultImagePath,
            title = "Všechny recepty"
        )

        val default = Category(
            id = -2,
            imageSrc = defaultImagePath,
            title = ""
        )

        val favorite = Category(
            id = -3,
            imageSrc = "categories/favorite.png",
            title = "Oblíbené"
        )

        val fast = Category(
            id = -4,
            imageSrc = "categories/stopwatch.png",
            title = "Rychlé recepty"
        )
        val others = Category(
            id = -5,
            imageSrc = "categories/others.png",
            title = "Ostatní"
        )
    }
}
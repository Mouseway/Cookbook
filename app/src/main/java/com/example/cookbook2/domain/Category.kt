package com.example.cookbook2.domain

import com.example.cookbook2.R

class Category(
    val id: Int = 0,
    val imageSrc: String,
    val title: String
) {
    companion object {

        private const val defaultImagePath = "categories/cookbook.png"

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
    }
}
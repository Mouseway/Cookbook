package com.example.cookbook2.data

import com.example.cookbook2.R

object RecipesResource {
    private val recipes = listOf<Recipe>(
        Recipe(
            1,
            "Palačinky",
            R.drawable.pancake,
            "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur vitae diam non enim vestibulum interdum. Donec ipsum massa, ullamcorper in, auctor et, scelerisque sed, est. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?",
            listOf(Pair("banán", "2 ks"), Pair("mléko", "500 ml"), Pair("vejce", "4 ks")),
            listOf(
                "Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?",
                "Nteger tempor. Donec vitae arcu. Etiam sapien elit, consequat eget, tristique non, venenatis quis, ante. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium",
                "Doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Sed convallis magna eu sem. Duis viverra diam non justo. Aliquam erat volutpat. Mauris dolor felis, sagittis at, luctus sed, aliquam non, tellus."
            ),
            25,
            3.5,
            3
        )
    )

    fun getAllRecipes() = recipes
    fun getById(id: Int) = recipes.find { it.id == id }
}
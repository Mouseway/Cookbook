package com.example.cookbook2.data.room.entities

import androidx.room.Embedded
import androidx.room.Relation

class RecipeRoom(
   @Embedded
   val recipe: RecipeInfoEntity,
   @Relation(
      parentColumn = "id",
      entityColumn = "recipeId"
   )
   val ingredients: List<IngredientItemEntity>,
) {}
package com.example.cookbook2.data.room.entities

import androidx.room.Embedded
import androidx.room.Relation


// Recipe entity is composed by RecipeInfoEntity and list of IngredientItem entities
class RecipeEntity(
   @Embedded
   val recipe: RecipeInfoEntity,
   @Relation(
      parentColumn = "id",
      entityColumn = "recipeId"
   )
   val ingredients: List<IngredientItemEntity>,
) {}
package com.example.cookbook2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cookbook2.data.room.daos.CategoryDao
import com.example.cookbook2.data.room.daos.RecipeDao
import com.example.cookbook2.data.room.entities.CategoryEntity
import com.example.cookbook2.data.room.entities.CategoryRecipeCrossRef
import com.example.cookbook2.data.room.entities.IngredientItemEntity
import com.example.cookbook2.data.room.entities.RecipeInfoEntity

@Database(entities = [RecipeInfoEntity::class, IngredientItemEntity::class, CategoryEntity::class, CategoryRecipeCrossRef::class], version = 1)
abstract class CookbookDatabase : RoomDatabase(){
    abstract val recipeDao: RecipeDao
    abstract val categoryDao: CategoryDao
}
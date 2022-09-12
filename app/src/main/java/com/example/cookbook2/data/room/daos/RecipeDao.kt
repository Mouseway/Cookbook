package com.example.cookbook2.data.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cookbook2.data.room.entities.CategoryRecipeCrossRef
import com.example.cookbook2.data.room.entities.IngredientItemEntity
import com.example.cookbook2.data.room.entities.RecipeInfoEntity
import com.example.cookbook2.data.room.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert
    suspend fun insert(recipeInfo: RecipeInfoEntity): Long

    @Insert
    suspend fun insert(recipeIngredientItemEntity: IngredientItemEntity): Long

    @Update
    suspend fun update(recipeInfo: RecipeInfoEntity)

    @Update
    suspend fun update(recipeIngredientItemEntity: IngredientItemEntity)

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe WHERE id=:id")
    fun getRecipeById(id: Int): LiveData<RecipeEntity>

    @Query("SELECT * FROM recipe"
    + " INNER JOIN categoryrecipecrossref ON recipeId == id"
    + " WHERE categoryId == :categoryId"
    )
    fun getByCategoryId(categoryId: Int): Flow<List<RecipeEntity>>

    @Insert
    suspend fun addCategoryToRecipe(ref: CategoryRecipeCrossRef)

    @Transaction
    @Query("SELECT * FROM recipe WHERE favorite = 1")
    fun getAllFavorite(): Flow<List<RecipeEntity>>

    @Transaction
    @Query("SELECT * FROM recipe WHERE time <= :time")
    fun getLessThanMinutes(time: Int): Flow<List<RecipeEntity>>


    @Transaction
    @Query("SELECT * " +
            "FROM recipe r " +
            "LEFT JOIN categoryrecipecrossref c ON r.id = c.recipeId " +
            "WHERE c.categoryId IS NULL")
    fun getAllWithoutCategory(): Flow<List<RecipeEntity>>
}
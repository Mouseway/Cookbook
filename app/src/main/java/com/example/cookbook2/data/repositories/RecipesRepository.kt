package com.example.cookbook2.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cookbook2.data.room.daos.RecipeDao
import com.example.cookbook2.data.room.entities.CategoryRecipeCrossRef
import com.example.cookbook2.data.room.entities.IngredientItemEntity
import com.example.cookbook2.data.room.entities.RecipeInfoEntity
import com.example.cookbook2.data.room.entities.RecipeRoom
import com.example.cookbook2.domain.IngredientItem
import com.example.cookbook2.domain.Recipe
import com.example.cookbook2.utils.FavoriteState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipesRepository(private val dao: RecipeDao) {

    private val stepsSeparator = "\n"

    fun getByCategory(categoryId: Int): Flow<List<Recipe>>{
        return dao.getByCategoryId(categoryId).map { list ->
            list.map { roomRecipe ->
                roomRecipeToRecipe(roomRecipe)
            }
        }
    }

    suspend fun addCategoryToRecipe(recipeId: Int, categoryId: Int){
        dao.addCategoryToRecipe(CategoryRecipeCrossRef(categoryId, recipeId))
    }

    suspend fun insert(recipe: Recipe){
        val id = dao.insert(recipeToRecipeInfo(recipe)).toInt()
        recipe.ingredients.forEach {
            dao.insert(ingredientToIngredientEntity(it, id))
        }
    }

    private fun recipeToRecipeInfo(recipe: Recipe): RecipeInfoEntity{
        return RecipeInfoEntity(
            id = recipe.id,
            title = recipe.title,
            description = recipe.description,
            time = recipe.time,
            difficulty = recipe.difficulty,
            imageSrc = recipe.imageSrc,
            servings = recipe.servings,
            steps = joinSteps(recipe.steps),
            favorite = when(recipe.favorite){
                FavoriteState.FAVORITE -> true
                else -> false
            }
        )
    }

    private fun ingredientToIngredientEntity(ingredient: IngredientItem, recipeId: Int): IngredientItemEntity{
        return IngredientItemEntity(
            id = ingredient.id,
            recipeId = recipeId,
            amount = ingredient.amount,
            foodstuff = ingredient.foodstuff
        )
    }

    private fun roomRecipeToRecipe(roomRecipe: RecipeRoom): Recipe {
        return Recipe(
            id = roomRecipe.recipe.id,
            title = roomRecipe.recipe.title,
            description = roomRecipe.recipe.description,
            servings = roomRecipe.recipe.servings,
            time = roomRecipe.recipe.time,
            imageSrc = roomRecipe.recipe.imageSrc,
            difficulty = roomRecipe.recipe.difficulty,
            steps = parseSteps(roomRecipe.recipe.steps),
            ingredients = roomRecipe.ingredients.map { IngredientItem(it.id, it.amount, it.foodstuff) },
            favorite = if(roomRecipe.recipe.favorite) FavoriteState.FAVORITE else FavoriteState.NOT_FAVORITE
        )
    }

    private fun parseSteps(steps: String): List<String>{
        return steps.split(stepsSeparator)
    }

    private fun joinSteps(steps: List<String>): String{
        return steps.joinToString(stepsSeparator)
    }


    fun getAllRecipes(): Flow<List<Recipe>> {
        return dao.getAllRecipes().map { list ->
            list.map { roomRecipe ->
                roomRecipeToRecipe(roomRecipe)
            }
        }
    }

    suspend fun getRecipeById(id: Int): LiveData<Recipe> {
        val roomRecipeLiveData = dao.getRecipeById(id)
        return Transformations.map(roomRecipeLiveData
        ) { roomRecipe ->
            roomRecipeToRecipe(roomRecipe)
        }
    }

    suspend fun updateRecipeInfo(recipe: Recipe){
        val recipeInfo = recipeToRecipeInfo(recipe)
        dao.update(recipeInfo)
    }
}
package com.example.cookbook2.data.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cookbook2.data.room.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(category: CategoryEntity)

    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<CategoryEntity>>


    @Query("SELECT * FROM category WHERE id=:categoryId")
   suspend  fun getCategoryById(categoryId: Int): CategoryEntity

}
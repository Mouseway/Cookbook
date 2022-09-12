package com.example.cookbook2.data.repositories

import com.example.cookbook2.data.room.daos.CategoryDao
import com.example.cookbook2.data.room.entities.CategoryEntity
import com.example.cookbook2.domain.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoriesRepository(private val dao: CategoryDao) {

    fun getAllCategories(): Flow<List<Category>> {
        return dao.getAll().map { list ->
            list.map {
                entityToCategory(it)
            }
        }
    }

    suspend fun insertCategory(category: Category){
        dao.insert(categoryToEntity(category))
    }

    private fun entityToCategory(categoryEntity: CategoryEntity): Category {
        return Category(
            id = categoryEntity.id,
            imageSrc = categoryEntity.imageSrc,
            title = categoryEntity.title
        )
    }

    private fun categoryToEntity(category: Category): CategoryEntity{
        return CategoryEntity(
            id = category.id,
            title = category.title,
            imageSrc = category.imageSrc
        )
    }

    suspend fun getCategoryById(categoryId: Int): Category {
        return when(categoryId){
            Category.all.id -> Category.all
            Category.favorite.id -> Category.favorite
            Category.fast.id -> Category.fast
            Category.others.id -> Category.others
            else -> entityToCategory(dao.getCategoryById(categoryId))
        }
    }
}



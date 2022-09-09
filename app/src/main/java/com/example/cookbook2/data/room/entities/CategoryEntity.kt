package com.example.cookbook2.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val imageSrc: String,
    val title: String
) {
}
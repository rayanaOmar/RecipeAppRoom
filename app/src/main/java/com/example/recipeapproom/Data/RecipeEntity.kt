package com.example.recipeapproom.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity (tableName = "Recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "Ingredients") var ingredients: String,
    @ColumnInfo(name = "Instruction") var instruction: String
)
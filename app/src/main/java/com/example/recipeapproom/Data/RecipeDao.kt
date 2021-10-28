package com.example.recipeapproom.Data

import androidx.room.*


@Dao
interface RecipeDao{
    @Query("SELECT * FROM Recipe ORDER BY title DESC")
    fun getAllUserInfo(): List<RecipeEntity>

    @Insert
    fun insertRecipe(recipe: RecipeEntity)

    @Update
    fun updateRecipe(recipe: RecipeEntity)

    @Delete
    fun deleteRecipe(recipe: RecipeEntity)
}
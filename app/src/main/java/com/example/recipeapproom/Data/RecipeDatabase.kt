package com.example.recipeapproom.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities =[RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{
        var instant: RecipeDatabase?= null
        fun getInstant(context: Context): RecipeDatabase{
            if(instant != null){
                return instant as RecipeDatabase
            }
            instant = Room.databaseBuilder(context, RecipeDatabase::class.java, "name").run {
                allowMainThreadQueries() }.build()
            return instant as RecipeDatabase
        }
    }
    abstract fun RecipeDao(): RecipeDao;
}
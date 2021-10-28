package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapproom.Data.RecipeDatabase
import com.example.recipeapproom.Data.RecipeEntity

class MainActivity2 : AppCompatActivity() {

    lateinit var addNew: Button
    lateinit var textView: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var list: List<RecipeEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        addNew = findViewById(R.id.button)
        addNew.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        textView = findViewById(R.id.textView)
        recyclerView = findViewById(R.id.rv)
        list = listOf()

        updateRV()
    }
    private fun updateRV(){
        val recipeDB = RecipeDatabase.getInstant(applicationContext)
        val r = recipeDB.RecipeDao().getAllUserInfo()

        recyclerView.adapter = RVadapter(this, r)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun updateRecipe(recipe: RecipeEntity){
        val recipeDB = RecipeDatabase.getInstant(applicationContext)
        var recipeUp = recipe
        val dialog = AlertDialog.Builder(this)
        lateinit var input: EditText
        lateinit var tb1: EditText
        lateinit var tb2: EditText
        lateinit var tb3: EditText
        lateinit var view: View

        dialog.setCancelable(false)
        dialog.setPositiveButton("update"){_,_ ->
            recipeUp.title = input.text.toString()
            recipeUp.author = tb1.text.toString()
            recipeUp.ingredients = tb2.text.toString()
            recipeUp.instruction = tb3.text.toString()
            recipeDB.RecipeDao().updateRecipe(RecipeEntity(recipeUp.id,recipeUp.title,recipeUp.author,
                recipeUp.ingredients, recipeUp.instruction))
            updateRV()
        }.setNegativeButton("Cancel"){
            dialog, _ -> dialog.cancel()
        }
        val alert = dialog.create()
        alert.setTitle("Edit celebrity")
        view=layoutInflater.inflate(R.layout.activity_main3,null)
        alert.setView(view)
        input= view.findViewById(R.id.input)
        tb1=view.findViewById(R.id.tb1)
        tb2=view.findViewById(R.id.tb2)
        tb3=view.findViewById(R.id.tb3)

        input.setText(recipeUp.title)
        tb1.setText(recipeUp.author)
        tb2.setText(recipeUp.ingredients)
        tb3.setText(recipeUp.instruction)

        alert.show()

    }

    fun deleteRecipe(recipe: RecipeEntity){
        val recipeDB = RecipeDatabase.getInstant(applicationContext)
        recipeDB.RecipeDao().deleteRecipe(recipe)
        updateRV()

        Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()

    }
}
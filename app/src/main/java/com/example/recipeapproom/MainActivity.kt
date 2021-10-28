package com.example.recipeapproom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.recipeapproom.Data.RecipeDatabase
import com.example.recipeapproom.Data.RecipeEntity

class MainActivity : AppCompatActivity() {

    lateinit var title: EditText
    lateinit var name: EditText
    lateinit var ingredients: EditText
    lateinit var instruction: EditText

    lateinit var saveBtn: Button
    lateinit var viewBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipeDB = RecipeDatabase.getInstant(applicationContext)

        title = findViewById(R.id.titleEd)
        name = findViewById(R.id.nameEd)
        ingredients = findViewById(R.id.ingerEd)
        instruction = findViewById(R.id.instEd)
        saveBtn = findViewById(R.id.saveBtn)
        viewBtn = findViewById(R.id.viewBtn)

        saveBtn.setOnClickListener {
            var s1 = title.text.toString()
            var s2=name.text.toString()
            var s3=ingredients.text.toString()
            var s4=instruction.text.toString()
            if(s1.isNotEmpty()&& s2.isNotEmpty()&& s3.isNotEmpty()&&s4.isNotEmpty()){
                recipeDB.RecipeDao().insertRecipe(RecipeEntity(0,s1,s2,s3,s4))
                title.text.clear()
                name.text.clear()
                ingredients.text.clear()
                instruction.text.clear()

                Toast.makeText(applicationContext, "Successfully Added", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "One or More Filed is Empty!!", Toast.LENGTH_LONG).show()
            }
        }

        viewBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
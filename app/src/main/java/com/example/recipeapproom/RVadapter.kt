package com.example.recipeapproom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapproom.Data.RecipeEntity
import kotlinx.android.synthetic.main.item_row.view.*

class RVadapter(val activity: MainActivity2, val recipes: List<RecipeEntity>) : RecyclerView.Adapter<RVadapter.recyclerViewHolder>() {
    class recyclerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
        return recyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.itemView.apply {

            textview2.text = "${recipe.title}\n${recipe.author}\n${recipe.ingredients}\n${recipe.instruction}\n"

            editImage.setOnClickListener {
                activity.updateRecipe(recipe)
            }
            deleteImage.setOnClickListener {
                activity.deleteRecipe(recipe)
            }
        }
    }

    override fun getItemCount()=recipes.size
}

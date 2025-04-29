package com.example.aseducationalproject.Recipes

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aseducationalproject.Domain.Recipe
import com.example.aseducationalproject.R
import com.example.aseducationalproject.databinding.ItemCategoryBinding


class RecipeListAdapter(private val dataSet: List<Recipe>) :
    RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(recipeId: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCategoryBinding.bind(view)

        val imageView: ImageView = binding.ivItemCategory
        val titleTextView: TextView = binding.tvItemCategory




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        // val inflater = LayoutInflater.from(viewGroup.context)
        //val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category, viewGroup, false)
        println("// Я переводит xml  в объект\n")

        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val recipe: Recipe = dataSet[position]
        holder.titleTextView.text = recipe.title


        val drawable = try {
            Drawable.createFromStream(holder.itemView.context.assets.open(recipe.imageUrl), null)
        } catch (e: Exception) {
            Log.d("Not found", "Image not found: ${recipe.imageUrl}")
            null
        }

        holder.imageView.setImageDrawable(drawable)
        holder.imageView.contentDescription = recipe.title

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(recipe.id)
        }

    }
    override fun getItemCount(): Int = dataSet.size

}
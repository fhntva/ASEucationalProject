package com.example.aseducationalproject.ui.recipes.recipeList

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aseducationalproject.model.Recipe
import com.example.aseducationalproject.R
import com.example.aseducationalproject.databinding.ItemRecipeBinding


class RecipesListAdapter(val dataSet: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    fun interface OnItemClickListener {
        fun onItemClick(recipeId: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("StringFormatInvalid")
        fun bind(recipe: Recipe) {
            binding.tvRecipeName.text = recipe.title

            try {
                val drawable = Drawable.createFromStream(
                    itemView.context.assets.open(recipe.imageUrl),
                    null
                )
                binding.ivRecipe.setImageDrawable(drawable)
                binding.ivRecipe.contentDescription = String.format(
                    itemView.context.getString(R.string.recipe_image_description),
                    recipe.title
                )
            } catch (e: Exception) {
                Log.e("RecipesListFragment", "Ошибка загрузки изображения: ${recipe.title}", e)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = ItemRecipeBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val recipe = dataSet[position]
        viewHolder.bind(recipe)

        viewHolder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(recipeId = recipe.id)
        }
    }

    override fun getItemCount() = dataSet.size
}

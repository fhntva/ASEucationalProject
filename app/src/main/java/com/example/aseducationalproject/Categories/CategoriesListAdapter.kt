package com.example.aseducationalproject.Categories

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aseducationalproject.Domain.Category
import com.example.aseducationalproject.R
import com.example.aseducationalproject.databinding.ItemCategoryBinding


class CategoryListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemCategoryBinding.bind(view)
        val imageView: ImageView = binding.ivItemCategory
        val titleTextView: TextView = binding.tvItemCategory
        val descriptionTextView: TextView = binding.tvCategoryDescription

        // поддягивает нужные данные по id


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        // val inflater = LayoutInflater.from(viewGroup.context)
        //val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_category, viewGroup, false)
        println("// Я переводит xml  в объект\n")

        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //return ViewHolder(view)
        return ViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category: Category = dataSet[position]
        holder.titleTextView.text = category.title
        holder.descriptionTextView.text = category.description




        val drawable = try {
            Drawable.createFromStream(holder.itemView.context.assets.open(category.imageUrl), null)
        } catch (e: Exception) {
            Log.d("Not found", "Image not found: ${category.imageUrl}")
            null
        }

        holder.imageView.setImageDrawable(drawable)
        holder.imageView.contentDescription = category.title
        holder.itemView.setOnClickListener {

            itemClickListener?.onItemClick(category.id)
        }

    }

    override fun getItemCount() = dataSet.size

}
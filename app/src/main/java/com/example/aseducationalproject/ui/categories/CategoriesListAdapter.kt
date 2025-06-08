package com.example.aseducationalproject.ui.categories

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aseducationalproject.model.Category
import com.example.aseducationalproject.R
import com.example.aseducationalproject.databinding.ItemCategoryBinding



class CategoriesListAdapter(private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(categoryId: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.tvCategoryName.text = category.title
            binding.tvCategoryDescription.text = category.description

            try {
                val drawable = Drawable.createFromStream(
                    itemView.context.assets.open(category.imageUrl),
                    null
                )
                binding.ivCategory.setImageDrawable(drawable)
                binding.ivCategory.contentDescription = String.format(
                    itemView.context.getString(R.string.category_image_content_description),
                    category.title
                )
            } catch (e: Exception) {
                Log.e("CategoriesListAdapter", "Ошибка загрузки изображения: ${category.title}", e)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = ItemCategoryBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val category = dataSet[position]
        viewHolder.bind(category)

        viewHolder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(categoryId = category.id)
        }
    }

    override fun getItemCount() = dataSet.size
}
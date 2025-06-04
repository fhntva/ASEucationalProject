package com.example.aseducationalproject.Recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aseducationalproject.Domain.Ingredient
import com.example.aseducationalproject.databinding.ItemIngredientBinding


class IngredientsAdapter(private val dataSet: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    private var quantity: Double = 1.0

    class ViewHolder(binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        val textView: TextView = binding.tvIngredient
        val quantity: TextView = binding.tvQuantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ingredient: Ingredient = dataSet[position]
        holder.textView.text = ingredient.description
        var updateQuant = ingredient.quantity.toBigDecimal().multiply(quantity.toBigDecimal())
            .stripTrailingZeros().toPlainString()
        holder.quantity.text = "${updateQuant} ${ingredient.unitOfMeasure}"
    }

    override fun getItemCount(): Int = dataSet.size

    fun updateIngredients(progress: Double) {
        quantity = progress
        notifyDataSetChanged()
    }

    companion object


}

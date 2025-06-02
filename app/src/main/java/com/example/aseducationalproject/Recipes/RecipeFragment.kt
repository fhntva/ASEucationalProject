package com.example.aseducationalproject.Recipes

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aseducationalproject.Categories.CategoriesListFragment.Companion.ARG_RECIPE
import com.example.aseducationalproject.DataTest.STUB
import com.example.aseducationalproject.databinding.FragmentRecipeBinding
import com.example.aseducationalproject.Domain.Recipe
import com.example.aseducationalproject.R
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment(R.layout.fragment_recipe) {


    private var _binding: FragmentRecipeBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must be not null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe: Recipe? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(ARG_RECIPE, Recipe::class.java)
        } else {
            requireArguments().getParcelable(ARG_RECIPE)
        }
        initUI(recipe)
        initRecycler(recipe)
    }

    fun initUI(recipe: Recipe?) {
        val drawable = try {
            Drawable.createFromStream(
                recipe?.imageUrl?.let { this.context?.assets?.open(it) }, null
            )
        } catch (e: Exception) {
            Log.d("Not found", "Image not found: ${recipe?.imageUrl}")
            null
        }

        if (recipe?.title == null) {
            recipe?.title == "я потерял ${recipe?.title}"
        }
        if (recipe?.title != null) {
            binding.tvRecipe.text = recipe.title
        }
        binding.ivRecipe.setImageDrawable(drawable)
    }

    private fun initRecycler(recipeId: Recipe?) {
        val id: Int = recipeId?.id ?: 1
        val ingredientsAdapter =
            IngredientsAdapter(STUB.getRecipeById(id)?.ingredients ?: emptyList())
        binding.rvIngredients.adapter = ingredientsAdapter
        val methodsAdapter = MethodsAdapther(STUB.getRecipeById(id)?.method ?: emptyList())
        binding.rvMethod.adapter = methodsAdapter
        val divider = MaterialDividerItemDecoration(
            this.requireContext(), LinearLayoutManager.VERTICAL
        ).apply {
            isLastItemDecorated = false
            dividerInsetStart = 24
            dividerInsetEnd = 24
            dividerColor = ContextCompat.getColor(requireContext(), R.color.recipe_fragment_color)
            dividerThickness = 6
        }

        binding.rvIngredients.addItemDecoration(divider)
        binding.rvMethod.addItemDecoration(divider)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

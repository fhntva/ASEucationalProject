package com.example.aseducationalproject.Recipes

import android.os.Build
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aseducationalproject.databinding.FragmentRecipeBinding
import com.example.aseducationalproject.Constants.ARG_RECIPE
import com.example.aseducationalproject.Domain.Recipe


class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipeBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for FragmentRecipesListBinding must be not null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(ARG_RECIPE, Recipe::class.java)
        } else {
            requireArguments().getParcelable(ARG_RECIPE)
        }

          binding.tvItemRecipe.text = recipe?.title


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

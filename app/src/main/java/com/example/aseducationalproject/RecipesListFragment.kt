package com.example.aseducationalproject

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aseducationalproject.databinding.FragmentRecipeListBinding

class RecipesListFragment : Fragment(R.layout.fragment_recipe_list) {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentRecipesListBinding must be not null")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

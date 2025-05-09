package com.example.aseducationalproject.Recipes

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.aseducationalproject.Constants.AGE_CATEGORY_NAME
import com.example.aseducationalproject.Constants.ARG_CATEGORY_ID
import com.example.aseducationalproject.Constants.ARG_CATEGORY_IMAGE_URL
import com.example.aseducationalproject.Constants.ARG_RECIPE
import com.example.aseducationalproject.DataTest.STUB
import com.example.aseducationalproject.DataTest.STUB.getRecipeById
import com.example.aseducationalproject.Domain.Recipe
import com.example.aseducationalproject.R
import com.example.aseducationalproject.databinding.FragmentRecipeListBinding

class RecipesListFragment : Fragment(R.layout.fragment_recipe_list) {

    private var _binding: FragmentRecipeListBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for FragmentRecipesListBinding must be not null")

    private var categoryId: Int? = null
    private var categoryName: String? = null
    private var categoryImageUrl: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeListBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryId = requireArguments().getInt(ARG_CATEGORY_ID)
        categoryName = requireArguments().getString(AGE_CATEGORY_NAME)
        categoryImageUrl = requireArguments().getString(ARG_CATEGORY_IMAGE_URL)
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecycler() {
        val recipeListAdapter = RecipeListAdapter(STUB.getRecipesByCategoryId(categoryId?:0))
        binding.rvRecipesList.adapter = recipeListAdapter
        recipeListAdapter.setOnItemClickListener(object : RecipeListAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                openRecipeByRecipeId(recipeId)
            }
        })
    }

    fun openRecipeByRecipeId(recipeId:Int) {
        val recipe: Recipe = getRecipeById(recipeId)

        val bundle = bundleOf(ARG_RECIPE to recipe)

        fragmentManager?.commit {
            setReorderingAllowed(true)
            replace<RecipeFragment>(R.id.mainContainer,args = bundle)
        }

    }


}

package com.example.aseducationalproject.ui.recipes.recipeList

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.aseducationalproject.ui.Const
import com.example.aseducationalproject.ui.Const.ARG_RECIPE
import com.example.aseducationalproject.DataTest.STUB
import com.example.aseducationalproject.DataTest.STUB.getRecipeById
import com.example.aseducationalproject.model.Recipe
import com.example.aseducationalproject.R
import com.example.aseducationalproject.databinding.FragmentRecipesListBinding
import com.example.aseducationalproject.ui.recipes.recipe.RecipeFragment

class RecipesListFragment : Fragment(R.layout.fragment_recipes_list) {
    private var argCategoryId: Int? = null
    private var argCategoryName: String? = null
    private var argCategoryImageUrl: String? = null

    private var _binding: FragmentRecipesListBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must be not null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBundleData()
        initUI()
        initRecycler()
    }

    fun initBundleData() {
        arguments?.let { args ->
            argCategoryId = args.getInt(Const.ARG_CATEGORY_ID)
            argCategoryName = args.getString(Const.ARG_CATEGORY_NAME)
            argCategoryImageUrl = args.getString(Const.ARG_CATEGORY_IMAGE_URL)
        }
    }

    fun initUI() {
        binding.tvBurgersRecipes.text = argCategoryName
        argCategoryImageUrl?.let { imageUrl ->
            try {
                val context = requireContext()
                val inputStream = context.assets.open(imageUrl)
                val drawable = Drawable.createFromStream(inputStream, null)
                binding.ivFragmentRecipeList.setImageDrawable(drawable)
                inputStream.close()
            } catch (e: Exception) {
                Log.e("argCategoryImageUrl", "Failed to load image from assets: $imageUrl")

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun openRecipesByRecipeId(recipeId: Int) {

        //
        val recipe: Recipe? = getRecipeById(recipeId)
        val bundle = bundleOf(ARG_RECIPE to recipe)
        //
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RecipeFragment>(R.id.mainContainer, args = bundle)
            addToBackStack(null)
        }
    }

    private fun initRecycler() {
        val adapter = RecipesListAdapter(STUB.getRecipesByCategoryId(argCategoryId ?: 0))
        binding.rvRecipes.adapter = adapter
        adapter.setOnItemClickListener(object : RecipesListAdapter.OnItemClickListener {
            override fun onItemClick(recipeId: Int) {
                openRecipesByRecipeId(recipeId)
            }
        })
    }


}


package com.example.aseducationalproject.Categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.aseducationalproject.Constants.AGE_CATEGORY_NAME
import com.example.aseducationalproject.Constants.ARG_CATEGORY_ID
import com.example.aseducationalproject.Constants.ARG_CATEGORY_IMAGE_URL
import com.example.aseducationalproject.R
import com.example.aseducationalproject.Recipes.RecipesListFragment
import com.example.aseducationalproject.DataTest.STUB
import com.example.aseducationalproject.databinding.FragmentListCategoriesBinding

class CategoriesListFragment :
    Fragment(R.layout.fragment_list_categories) {

    private var _binding: FragmentListCategoriesBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must be not null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCategoriesBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println(println("Я зашел в создание экрана "))
        initRecycler()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun initRecycler() {

        val categoriesAdapter = CategoryListAdapter(STUB.getCategories())
        binding.rvCategories.adapter = categoriesAdapter

        categoriesAdapter.setOnItemClickListener(object : CategoryListAdapter.OnItemClickListener{
            override fun onItemClick(id: Int) {
                openRecipesByCategoryId(id)
            }
        })


    }
    private fun openRecipesByCategoryId(categoryId: Int)
    {
        val category = STUB.getCategories()[categoryId]

        val bundle = bundleOf(
            ARG_CATEGORY_ID to categoryId,
            AGE_CATEGORY_NAME to category.title,
            ARG_CATEGORY_IMAGE_URL to category.imageUrl


        )

        fragmentManager?.commit {
            setReorderingAllowed(true)
            replace<RecipesListFragment>(R.id.mainContainer, args = bundle)
        }
    }



}
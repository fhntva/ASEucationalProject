package com.example.aseducationalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aseducationalproject.databinding.FragmentListCategoriesBinding


class CategoriesListFragment:
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
        println( println("Я зашел в создание экрана "))
        initRecycler()

    }


    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    fun initRecycler()
    {

        val categoriesAdapter = CategoryListAdapter(STUB.getCategories())
       binding.rvCategories.adapter = categoriesAdapter

    }

}


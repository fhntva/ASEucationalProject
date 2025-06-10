package com.example.aseducationalproject.ui.recipes.recipe

import androidx.fragment.app.viewModels
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aseducationalproject.ui.Const.ARG_RECIPE
import com.example.aseducationalproject.DataTest.STUB
import com.example.aseducationalproject.databinding.FragmentRecipeBinding
import com.example.aseducationalproject.model.Recipe
import com.example.aseducationalproject.R
import com.google.android.material.divider.MaterialDividerItemDecoration

class RecipeFragment : Fragment(R.layout.fragment_recipe) {

    private val viewModel: RecipeViewModel by viewModels()

    private lateinit var ingredientsAdapter: IngredientsAdapter
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
        viewModel.loadRecipe(recipe?.id ?: -1)
        initUI()
        initRecycler(recipe)
    }

    fun initUI() {
        viewModel.recipeState.observe(viewLifecycleOwner) { state ->
            state?.let {
                val drawable = try {
                    Drawable.createFromStream(
                        state.recipe?.imageUrl?.let { this.context?.assets?.open(it) }, null
                    )
                } catch (e: Exception) {
                    Log.d("Not found", "Image not found: ${state.recipe?.imageUrl}")
                    null
                }
                if (state.recipe?.title == null) {
                    state.recipe?.title == "я потерял ${state.recipe?.title}"
                }
                if (state.recipe?.title != null) {
                    binding.tvRecipe.text = state.recipe.title
                }

                if (state.isFavorite == true) {
                    binding.ibFavorite.setImageResource(R.drawable.ic_heart)
                } else {
                    binding.ibFavorite.setImageResource(R.drawable.ic_heart_empty)
                }
                binding.ibFavorite.setOnClickListener {
                    viewModel.onFavoriteClicked()
                }
                binding.ivRecipe.setImageDrawable(drawable)
                binding.sbFragmentRecipe.setOnSeekBarChangeListener(object :
                    SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        binding.tvFragmentRecipeNumber.text = progress.toString()
                        ingredientsAdapter.updateIngredients(progress.toDouble())
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }
                })
            }
        }
    }

    private fun initRecycler(recipeId: Recipe?) {
        val id: Int = recipeId?.id ?: 1
        ingredientsAdapter =
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
            dividerColor = ContextCompat.getColor(
                requireContext(), R.color.recipe_fragment_color
            )
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
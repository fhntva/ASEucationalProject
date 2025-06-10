package com.example.aseducationalproject.ui.recipes.recipe

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aseducationalproject.DataTest.STUB
import com.example.aseducationalproject.model.Recipe
import com.example.aseducationalproject.ui.FAVORITE_SET_KEY
import kotlin.toString

class RecipeViewModel(private val application: Application) : AndroidViewModel(application) {
    data class RecipeUIState(
        val recipe: Recipe? = null,
        val isFavorite: Boolean = false,
        val portionsCount: Int = 1,
    )

    private val privateRecipeState = MutableLiveData(RecipeUIState())
    val recipeState: LiveData<RecipeUIState>
        get() = privateRecipeState

    init {
        Log.d("RecipeViewModel", "isFavorite передано")
        privateRecipeState.value = RecipeUIState(isFavorite = true)
    }

    fun loadRecipe(id: Int) {
        if (id == -1) {
            Log.d("RecipeViewModel", "id == -1, download nothing  ")
            return
        }
        val recipe = STUB.getRecipeById(id)
        val favorites =
            getFavorites().getStringSet(FAVORITE_SET_KEY, mutableSetOf()) ?: mutableSetOf()
        val isFavorite = favorites.contains(id.toString())
        privateRecipeState.value = RecipeUIState(
            recipe = recipe,
            isFavorite = isFavorite,
        )
        Log.d("RecipeViewModel", "privateRecipeState.value:${privateRecipeState.value}")
    }

    private fun getFavorites(): SharedPreferences {
        return application.getSharedPreferences(FAVORITE_SET_KEY, Context.MODE_PRIVATE)
    }

    fun loadFavorites(): List<Recipe> {
        return privateLoadFavorites()
    }

    private fun privateLoadFavorites(): List<Recipe> {
        val favoritesPrefs = getFavorites()
        val favoritesList = favoritesPrefs.getStringSet(FAVORITE_SET_KEY, emptySet())
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet() ?: emptySet()
        return STUB.getRecipesByIds(favoritesList)
    }

    fun onFavoriteClicked() {
        val currentState = privateRecipeState.value ?: return
        val recipeId = currentState.recipe?.id ?: return

        val favorites =
            getFavorites().getStringSet(FAVORITE_SET_KEY, mutableSetOf())?.toMutableSet()
                ?: mutableSetOf()
        val newFavoriteState = !currentState.isFavorite

        if (newFavoriteState) {
            favorites.add(recipeId.toString())
        } else {
            favorites.remove(recipeId.toString())
        }
        saveFavorites(favorites)
        privateRecipeState.value = currentState.copy(isFavorite = newFavoriteState)
    }

    fun saveFavorites(favorites: MutableSet<String>) {
        getFavorites().edit() {
            putStringSet(FAVORITE_SET_KEY, favorites)
            apply()
        }
    }

}
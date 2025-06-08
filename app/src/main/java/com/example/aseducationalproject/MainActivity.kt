package com.example.aseducationalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.aseducationalproject.ui.categories.CategoriesListFragment
import com.example.aseducationalproject.databinding.ActivityMainBinding
import com.example.aseducationalproject.ui.recipes.favorites.FavoritesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CategoriesListFragment>(R.id.mainContainer)
            }
        }
        binding.btmCategoryButton.setOnClickListener {
            supportFragmentManager.commit {
                replace<CategoriesListFragment>(R.id.mainContainer)
                setReorderingAllowed(true)
                addToBackStack("name")
            }
        }
        binding.btmFavoritesButton.setOnClickListener {
            supportFragmentManager.commit {
                replace<FavoritesFragment>(R.id.mainContainer)
                setReorderingAllowed(true)
                addToBackStack("name")
            }
        }
    }
}
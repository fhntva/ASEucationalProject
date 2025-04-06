package com.example.aseducationalproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.aseducationalproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // что за метод onCreate()?
    //что? Реализовать фон основного лейаута в соответствии с цветом макет
    //Стилизовать кнопки, вынести в стили оформление текста кнопок.
    //7 туда же


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // обращение по разметке
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            if (savedInstanceState == null) {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    add<CategoriesListFragment>(R.id.mainContainer)
                    println("Я мейн окно")
                }


                }
                binding.btnCategoryMain.setOnClickListener {
                    supportFragmentManager.commit {
                        replace<CategoriesListFragment>(R.id.mainContainer)
                        //setReorderingAllowed(true)
                        addToBackStack("name")
                        println("Я btnCategoryMain")
                    }
                }
                binding.btnFavoritesMain.setOnClickListener {
                    supportFragmentManager.commit {
                        replace<FragmentFavorites>(R.id.mainContainer)
                        setReorderingAllowed(true)

                        println("Я btnFavoritesMain")



                    }
                }

            }
        }














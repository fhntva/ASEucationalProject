package com.example.aseducationalproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        //

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CategoriesListFragment>(R.id.mainContainer)
            }

        binding.btnCategoryMain.setOnClickListener {
            supportFragmentManager.commit {
                replace<CategoriesListFragment>(R.id.mainContainer)
                setReorderingAllowed(true)
                addToBackStack("name")
            }
        }
            binding.btnFavoritesMain.setOnClickListener {
                supportFragmentManager.commit {
                    replace<FragmentFavorites>(R.id.mainContainer)
                    setReorderingAllowed(true)

                }
            }



        }
    }

}
    //            enableEdgeToEdge()
//            setContentView(R.layout.activity_main)
//            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//                insets








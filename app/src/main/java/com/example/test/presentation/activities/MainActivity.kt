package com.example.test.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity of the main screen
 *
 * @property binding binds our activity to the layout
 *
 * @author Evgen K.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bnvNavigation.setupWithNavController(navController)

        binding.bnvNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_popular_currencies -> {
                    navController.navigate(R.id.fragment_popular_currencies)
                    true
                }
                R.id.fragment_favorite_currencies -> {
                    navController.navigate(R.id.fragment_favorite_currencies)
                    true
                }
                else -> false
            }
        }
    }
}
package com.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.newsapp.R
import com.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
        Log.d("MESSAGE", "CREATE")
    }

    private fun setupBottomNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.newsDetailFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                R.id.webViewFragment -> {
                    binding.bottomNavigation.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavigation.visibility = View.VISIBLE
                }
            }
        }

    }


    override fun onStart() {
        super.onStart()
        Log.d("MESSAGE", "START")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MESSAGE", "RESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MESSAGE", "PAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MESSAGE", "STOP")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MESSAGE", "RESTART")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MESSAGE", "DESTROYED")
    }
}
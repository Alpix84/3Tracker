package com.example.a3tracker.Activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a3tracker.R
import com.example.a3tracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_activities_feed,R.id.navigation_tasks,R.id.navigation_groups,R.id.navigation_settings
            )
        )
        navView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_activities_feed -> {
                    navController.navigate(R.id.navigation_activities_feed)
                    true
                }

                R.id.navigation_groups -> {
                    navController.navigate(R.id.navigation_groups)
                    true
                }

                R.id.navigation_tasks -> {
                    navController.navigate(R.id.navigation_tasks)
                    true
                }

                R.id.navigation_settings -> {
                    navController.navigate(R.id.navigation_settings)
                    true
                }

                else -> false
            }

        }
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)
    }
}
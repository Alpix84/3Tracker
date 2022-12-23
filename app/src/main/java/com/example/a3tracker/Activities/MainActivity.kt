package com.example.a3tracker.Activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a3tracker.Activities.ui.activities.ActivitiesFeed
import com.example.a3tracker.Activities.ui.groups.GroupsFragment
import com.example.a3tracker.Activities.ui.settings.SettingsFragment
import com.example.a3tracker.Activities.ui.tasks.TasksFragment
import com.example.a3tracker.R
import com.example.a3tracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ActivitiesFeed())

        binding.bottomNavView.setOnItemReselectedListener {
            when(it.itemId){
                R.id.navigation_activities_feed -> replaceFragment(ActivitiesFeed())
                R.id.navigation_groups -> replaceFragment(GroupsFragment())
                R.id.navigation_tasks -> replaceFragment(TasksFragment())
                R.id.navigation_settings -> replaceFragment(SettingsFragment())
                else -> {
                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

}
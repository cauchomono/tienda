package com.ciclo4.teksell.ui.activities

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ciclo4.teksell.R
import com.ciclo4.teksell.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configNav()
        val fragmentManager = supportFragmentManager
    }

    fun configNav(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragContent) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bnvMenu).setupWithNavController(navController)
    }
}


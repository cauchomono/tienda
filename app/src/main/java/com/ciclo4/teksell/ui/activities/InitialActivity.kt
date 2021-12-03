package com.ciclo4.teksell.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ciclo4.teksell.R
import com.ciclo4.teksell.databinding.ActivityInitialBinding

private lateinit var binding: ActivityInitialBinding

class InitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}
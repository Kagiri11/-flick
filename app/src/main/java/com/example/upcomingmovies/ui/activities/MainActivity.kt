package com.example.upcomingmovies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.upcomingmovies.R
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_UpcomingMovies)
        setContentView(R.layout.activity_main)
        bottomNav.setupWithNavController(frgNavHost.findNavController())
    }
}
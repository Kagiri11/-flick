package com.example.upcomingmovies.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.upcomingmovies.R
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.ui.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var moviesViewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_UpcomingMovies)
        setContentView(R.layout.activity_main)
        val moviesRepository = MovieRepository()
        val application = requireNotNull(this).application
        val viewModelProviderFactory = ViewModelFactory(moviesRepository,application)
        moviesViewModel=ViewModelProvider(this,viewModelProviderFactory).get(MoviesViewModel::class.java)
        bottomNav.setupWithNavController(frgNavHost.findNavController())

    }
}
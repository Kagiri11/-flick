package com.example.upcomingmovies.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.upcomingmovies.repository.MovieRepository

class ViewModelFactory(val repo : MovieRepository, val application: Application)  : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(repo,application) as T
    }
}
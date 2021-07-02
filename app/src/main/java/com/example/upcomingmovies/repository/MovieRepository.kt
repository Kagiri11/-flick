package com.example.upcomingmovies.repository

import com.example.upcomingmovies.network.RetrofitInstance

class MovieRepository {
    suspend fun fetchPopularMovies()= RetrofitInstance.api.fetchMovies()

    suspend fun fetchTopRatedMovies()=RetrofitInstance.api.fetchTopRatedMovies()

    suspend fun fetchUpcomingMovies()= RetrofitInstance.api.fetchUpcomingMovies()
}
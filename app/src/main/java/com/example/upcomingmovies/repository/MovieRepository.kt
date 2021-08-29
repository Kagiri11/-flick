package com.example.upcomingmovies.repository

import com.example.upcomingmovies.network.RetrofitInstance

class MovieRepository {

    suspend fun fetchPopularMovies()= RetrofitInstance.api.fetchMovies()

    suspend fun fetchTopRatedMovies()=RetrofitInstance.api.fetchTopRatedMovies()

    suspend fun fetchUpcomingMovies()= RetrofitInstance.api.fetchUpcomingMovies()

    suspend fun fetchMovieDetails(movieId:Int)=RetrofitInstance.api.fetchMovieDetails(movieId)

    suspend fun fetchSimilarMovies(movieId: Int)=RetrofitInstance.api.fetchSimilarMovies(movieId)

    suspend fun fetchMovieCast(movieId:Int)=RetrofitInstance.api.fetchMovieCast(movieId)

    suspend fun fetchMovieReviews(movieId:Int)=RetrofitInstance.api.fetchMovieReviews(movieId)

    suspend fun searchMovies(query:String) = RetrofitInstance.api.searchMovies(query)
}
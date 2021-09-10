package com.example.upcomingmovies.repository


import com.example.upcomingmovies.data.local.MovieDatabase
import com.example.upcomingmovies.data.local.CachedMovie
import com.example.upcomingmovies.data.local.MovieDatabaseDao
import com.example.upcomingmovies.data.network.RetrofitInstance

class MovieRepository{
    private val dao = MovieDatabase.INSTANCE?.movieDatabaseDao

    fun getMoviesCount()= dao?.moviesCount()
    suspend fun fetchPopularMovies()= RetrofitInstance.api.fetchMovies()

    suspend fun fetchTopRatedMovies()=RetrofitInstance.api.fetchTopRatedMovies()

    suspend fun fetchUpcomingMovies()= RetrofitInstance.api.fetchUpcomingMovies()

    suspend fun fetchMovieDetails(movieId:Int)=RetrofitInstance.api.fetchMovieDetails(movieId)

    suspend fun fetchSimilarMovies(movieId: Int)=RetrofitInstance.api.fetchSimilarMovies(movieId)

    suspend fun fetchMovieCast(movieId:Int)=RetrofitInstance.api.fetchMovieCast(movieId)

    suspend fun fetchMovieReviews(movieId:Int)=RetrofitInstance.api.fetchMovieReviews(movieId)

    suspend fun searchMovies(query:String) = RetrofitInstance.api.searchMovies(query)

    suspend fun getMovies()= dao?.getFavourites()

    suspend fun addFavourite(movie: CachedMovie) = dao?.addFavourite(movie)
}
package com.example.upcomingmovies.network

import com.example.upcomingmovies.models.MovieResponse
import com.example.upcomingmovies.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiService {
    @GET("movie/popular")
    suspend fun fetchMovies(
        @Query("api_key")apiKey:String = API_KEY,
        @Query("page")pageNumber:Int=50,
    ):Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key")apiKey: String= API_KEY,
        @Query("page")pageNumber: Int = 25
    ):Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key")apiKey:String = API_KEY,
        @Query("page")pageNumber: Int=11
    ):Response<MovieResponse>

}
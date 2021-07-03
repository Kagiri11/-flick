package com.example.upcomingmovies.network

import com.example.upcomingmovies.models.MovieResponse
import com.example.upcomingmovies.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {
    @GET("movie/popular")
    suspend fun fetchMovies(
        @Query("api_key")apiKey:String = API_KEY,
        @Query("page")pageNumber:Int=11,
    ):Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key")apiKey: String= API_KEY,
        @Query("page")pageNumber: Int = 11,
        @Query("language")language :String = "en"
    ):Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key")apiKey:String = API_KEY,
        @Query("page")pageNumber: Int=11
    ):Response<MovieResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movie_id") movieId: Int=791373,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id")movieId : Int=791373,
        @Query("api_key")apiKey:String= API_KEY,
        @Query("language")language :String = "en"
    ):Response<MovieResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCast(
        @Path("movie_id")movieId: Int=791373,
        @Query("api_key")apiKey: String= API_KEY,
        @Query("language")language: String="en"
    ):Response<MovieResponse>

}
package com.example.data.remote

import com.example.data.remote.dto.MovieResponseDto
import com.example.domain.utils.Constants.Companion.API_KEY
import com.example.data.remote.dto.CastDetails
import com.example.data.remote.dto.MovieDetailsDto
import com.example.upcomingmovies.models.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {
    @GET("movie/popular")
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MovieResponseDto

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponseDto

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MovieResponseDto

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") pageNumber: Int = 1,
        @Query("language") language: String = "en"
    ): MovieResponseDto

    @GET("movie/{movie_id}/similar")
    suspend fun fetchSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MovieResponseDto

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): MovieDetailsDto

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): CastDetails

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en"
    ): ReviewResponse

}
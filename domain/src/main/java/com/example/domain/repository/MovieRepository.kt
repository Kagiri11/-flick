package com.example.domain.repository

import com.example.domain.utils.Constants
import com.example.upcomingmovies.models.CastDetails
import com.example.domain.models.MovieDetails
import com.example.upcomingmovies.models.MovieResponse
import com.example.upcomingmovies.models.ReviewResponse

interface MovieRepository {
    suspend fun fetchMovies() : com.example.data.remote.dto.MovieResponseDto
    suspend fun searchMovies(
        query :String,
        apiKey:String
    ): com.example.data.remote.dto.MovieResponseDto
    suspend fun fetchTopRatedMovies(
        apiKey: String,
        pageNumber: Int,
        language :String
    ): com.example.data.remote.dto.MovieResponseDto
    suspend fun fetchUpcomingMovies(
        apiKey:String ,
        pageNumber: Int,
        language :String
    ): com.example.data.remote.dto.MovieResponseDto
    suspend fun fetchSimilarMovies(
        movieId: Int,
        apiKey: String,
        language: String
    ): com.example.data.remote.dto.MovieResponseDto

    suspend fun fetchMovieDetails(
        movieId : Int,
        apiKey:String,
        language :String
    ): com.example.data.remote.dto.MovieDetailsDto
    suspend fun fetchMovieCast(
        movieId: Int,
        apiKey: String,
        language: String
    ): com.example.data.remote.dto.CastDetails

    suspend fun fetchMovieReviews(
        movieId: Int,
        apiKey: String= Constants.API_KEY,
        language: String="en"
    ):ReviewResponse
}
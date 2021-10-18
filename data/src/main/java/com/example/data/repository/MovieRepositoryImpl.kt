package com.example.data.repository

import com.example.data.remote.MovieApiService
import com.example.data.remote.RetrofitInstance
import com.example.domain.repository.MovieRepository
import com.example.data.remote.dto.CastDetails
import com.example.data.remote.dto.MovieDetailsDto
import com.example.domain.models.MovieDetails
import com.example.data.remote.dto.MovieResponseDto
import com.example.upcomingmovies.models.MovieResponse
import com.example.upcomingmovies.models.ReviewResponse

class MovieRepositoryImpl : MovieRepository {
    private val api =  RetrofitInstance.api
    override suspend fun fetchMovies(): MovieResponseDto {
        return api.fetchMovies()
    }

    override suspend fun searchMovies(query: String, apiKey: String): MovieResponseDto {
        return api.searchMovies(query,apiKey)
    }

    override suspend fun fetchTopRatedMovies(
        apiKey: String,
        pageNumber: Int,
        language: String
    ): MovieResponseDto {
        return api.fetchTopRatedMovies()
    }

    override suspend fun fetchUpcomingMovies(
        apiKey: String,
        pageNumber: Int,
        language: String
    ): MovieResponseDto {
        return api.fetchUpcomingMovies()
    }

    override suspend fun fetchSimilarMovies(
        movieId: Int,
        apiKey: String,
        language: String
    ): MovieResponseDto {
        return api.fetchSimilarMovies(movieId, apiKey, language)
    }

    override suspend fun fetchMovieDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): MovieDetailsDto {
        return api.fetchMovieDetails(movieId, apiKey, language)
    }

    override suspend fun fetchMovieCast(
        movieId: Int,
        apiKey: String,
        language: String
    ): CastDetails {
        return  api.fetchMovieCast(movieId, apiKey, language)
    }

    override suspend fun fetchMovieReviews(
        movieId: Int,
        apiKey: String,
        language: String
    ): ReviewResponse {
        return api.fetchMovieReviews(movieId, apiKey, language)
    }
}
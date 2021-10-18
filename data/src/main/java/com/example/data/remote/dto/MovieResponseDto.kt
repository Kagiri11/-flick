package com.example.data.remote.dto

import com.example.domain.models.Movie
import com.example.upcomingmovies.models.MovieResponse

data class MovieResponseDto(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

fun MovieResponseDto.toMovieResponse(): MovieResponse {
    return MovieResponse(
        page = page,
        results = results,
        total_pages = total_pages,
        total_results = total_results
    )
}
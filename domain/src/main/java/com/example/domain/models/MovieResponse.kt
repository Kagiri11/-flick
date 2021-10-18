package com.example.upcomingmovies.models

import com.example.domain.models.Movie

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
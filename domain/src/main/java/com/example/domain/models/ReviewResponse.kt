package com.example.upcomingmovies.models

data class ReviewResponse(
    val id: Int,
    val page: Int,
    val reviews: List<Review>,
    val total_pages: Int,
    val total_results: Int
)
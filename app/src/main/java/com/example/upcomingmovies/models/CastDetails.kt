package com.example.upcomingmovies.models

data class CastDetails(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
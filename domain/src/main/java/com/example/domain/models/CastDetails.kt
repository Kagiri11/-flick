package com.example.upcomingmovies.models

import com.example.domain.models.Cast

data class CastDetails(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
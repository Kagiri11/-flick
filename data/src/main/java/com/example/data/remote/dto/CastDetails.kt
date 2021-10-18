package com.example.data.remote.dto

import com.example.domain.models.Cast
import com.example.upcomingmovies.models.Crew

data class CastDetails(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)
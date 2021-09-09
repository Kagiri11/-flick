package com.example.upcomingmovies.data.mappers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_movie")
data class CachedMovie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
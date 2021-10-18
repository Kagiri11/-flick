package com.example.data.remote.dto

import com.example.domain.models.MovieDetails
import com.example.upcomingmovies.models.Genre
import com.example.upcomingmovies.models.ProductionCompany
import com.example.upcomingmovies.models.ProductionCountry
import com.example.upcomingmovies.models.SpokenLanguage

data class MovieDetailsDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        backdrop_path = backdrop_path,
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        runtime = runtime,
        title = title, vote_average = vote_average, vote_count = vote_count
    )
}
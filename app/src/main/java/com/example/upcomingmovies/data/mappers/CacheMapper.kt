package com.example.upcomingmovies.data.mappers

import com.example.upcomingmovies.data.local.CachedMovie
import com.example.upcomingmovies.models.Movie

class CacheMapper:EntityMapper<CachedMovie,Movie> {
    override fun mapFromEntry(entity: CachedMovie): Movie {
        return Movie(
            id = entity.id,
            adult = entity.adult,
            backdrop_path = entity.backdrop_path,
            genre_ids = entity.genre_ids,
            original_language=entity.original_language,
            original_title=entity.original_title,
            overview = entity.overview,
            popularity=entity.popularity,
            poster_path = entity.poster_path,
            release_date = entity.release_date,
            title = entity.title,
            video = entity.video,
            vote_average = entity.vote_average,
            vote_count = entity.vote_count
        )
    }

    override fun mapToEntity(domainModel: Movie): CachedMovie {
        return CachedMovie(
            id = domainModel.id,
            adult = domainModel.adult,
            backdrop_path = domainModel.backdrop_path,
            genre_ids = domainModel.genre_ids,
            original_language=domainModel.original_language,
            original_title=domainModel.original_title,
            overview = domainModel.overview,
            popularity=domainModel.popularity,
            poster_path = domainModel.poster_path,
            release_date = domainModel.release_date,
            title = domainModel.title,
            video = domainModel.video,
            vote_average = domainModel.vote_average,
            vote_count = domainModel.vote_count
        )
    }

    fun mapToDomainList(cachedList:List<CachedMovie>):List<Movie>{
        return cachedList.map { cachedMovie->
            mapFromEntry(cachedMovie)
        }
    }

    fun mapToEntityList(domainList:List<Movie>):List<CachedMovie>{
        return domainList.map { movie->
            mapToEntity(movie)

        }
    }
}
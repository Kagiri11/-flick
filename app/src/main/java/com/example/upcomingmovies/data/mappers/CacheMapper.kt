package com.example.upcomingmovies.data.mappers

import com.example.upcomingmovies.models.Movie

class CacheMapper:EntityMapper<CachedMovie,Movie> {
    override fun mapFromEntry(entity: CachedMovie): Movie {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: Movie): CachedMovie {
        TODO("Not yet implemented")
    }
}
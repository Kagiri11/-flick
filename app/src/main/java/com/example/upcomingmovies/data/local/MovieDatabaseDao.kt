package com.example.upcomingmovies.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.upcomingmovies.data.mappers.CachedMovie
import com.example.upcomingmovies.models.Movie

@Dao
interface MovieDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(movie: CachedMovie)

    @Query("SELECT * FROM cached_movie ORDER BY id")
    fun getFavourites(): List<CachedMovie>
}
package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(movie: CachedMovie)

    @Query("SELECT * FROM cached_movie ORDER BY id")
    suspend fun getFavourites(): List<CachedMovie>

    @Query("SELECT * FROM cached_movie ORDER BY id")
    fun moviesCount():Int{
        return listOf<CachedMovie>().count()
    }

}
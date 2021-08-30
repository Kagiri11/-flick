package com.example.upcomingmovies.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.upcomingmovies.models.Movie

@Dao
interface MovieDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavourite(movie: Movie)

    @Query("SELECT * FROM movie ORDER BY id DESC")
    fun getFavourites(): List<Movie>
}
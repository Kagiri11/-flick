package com.example.upcomingmovies.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.upcomingmovies.models.Movie

@Database(entities = [Movie::class],version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract val movieDatabaseDao:MovieDatabaseDao

    companion object{
        @Volatile
        var INSTANCE : MovieDatabase?=null
        val DATABASE_NAME= "movie_db"
        fun getInstance(context: Context) : MovieDatabase{
            synchronized(this){
                var instance= INSTANCE

                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return  instance
            }
        }
    }

}
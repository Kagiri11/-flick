package com.example.data.local

import androidx.room.TypeConverter

object Konverter {

    @TypeConverter
    fun fromGenreList(genres:List<Int>):Int{
        return genres[0]
    }

    @TypeConverter
    fun toGenreList(genre:Int):List<Int>{
        return listOf(genre)
    }
}
package com.example.data

import com.example.data.remote.MovieApiService
import com.example.data.repository.MovieRepositoryImpl
import org.koin.dsl.module

val networkModule = module {
    single { MovieRepositoryImpl(get()) }
}
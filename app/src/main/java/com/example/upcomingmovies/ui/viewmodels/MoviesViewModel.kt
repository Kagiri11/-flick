package com.example.upcomingmovies.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.upcomingmovies.models.MovieResponse
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel(
    private val movieRepository: MovieRepository,
    application: Application
) : AndroidViewModel(application) {

    val popularMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val topRatedMovies : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val upcomingMovies : MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    val movieDetails = liveData {
        emit(movieRepository.fetchMovieDetails())
    }
    init {
        fetchPopularMovies()
        fetchTopRatedMovies()
        fetchUpcomingMovies()

    }

    private fun fetchPopularMovies()=viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchPopularMovies()
        popularMovies.postValue(handleMoviesResponse(response))

    }

    private fun fetchTopRatedMovies()=viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchTopRatedMovies()
        topRatedMovies.postValue(handleMoviesResponse(response))
    }

    private fun fetchUpcomingMovies()=viewModelScope.launch {
        upcomingMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchUpcomingMovies()
        upcomingMovies.postValue(handleMoviesResponse(response))
    }
    

    private fun handleMoviesResponse(response: Response<MovieResponse>):Resource<MovieResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }
}
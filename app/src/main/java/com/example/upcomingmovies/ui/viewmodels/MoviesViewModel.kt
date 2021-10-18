package com.example.upcomingmovies.ui.viewmodels


import androidx.lifecycle.*
import com.example.data.local.CachedMovie
import com.example.data.remote.dto.MovieResponseDto
import com.example.upcomingmovies.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel() : ViewModel() {
    private val movieRepository=MovieRepository()
    val popularMovies: MutableLiveData<Resource<MovieResponseDto>> = MutableLiveData()
    val topRatedMovies: MutableLiveData<Resource<MovieResponseDto>> = MutableLiveData()
    val upcomingMovies: MutableLiveData<Resource<MovieResponseDto>> = MutableLiveData()

    private val _searchedMovies: MutableLiveData<Resource<MovieResponseDto>> = MutableLiveData()
    val searchedMovies: LiveData<Resource<MovieResponseDto>> = _searchedMovies

    val favouriteMoviesCount = liveData {
        emit(movieRepository.getMoviesCount())
    }

    val favouriteMovies = liveData {
        emit(movieRepository.getMovies())
    }

    fun searchMovies(query: String) = viewModelScope.launch {
        _searchedMovies.postValue(Resource.Loading())
        val response = movieRepository.searchMovies(query)
        _searchedMovies.postValue(handleMoviesResponse(response))
    }

    suspend fun getFavourites(): List<CachedMovie>? = movieRepository.getMovies()


     fun fetchPopularMovies() = viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchPopularMovies()
        popularMovies.postValue(handleMoviesResponse(response))
    }

     fun fetchTopRatedMovies() = viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchTopRatedMovies()
        topRatedMovies.postValue(handleMoviesResponse(response))
    }

     fun fetchUpcomingMovies() = viewModelScope.launch {
        upcomingMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchUpcomingMovies()
        upcomingMovies.postValue(handleMoviesResponse(response))
    }

    private fun handleMoviesResponse(response: Response<MovieResponseDto>): Resource<MovieResponseDto> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}
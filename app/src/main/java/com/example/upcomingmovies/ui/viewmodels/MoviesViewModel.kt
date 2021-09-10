package com.example.upcomingmovies.ui.viewmodels


import androidx.lifecycle.*
import com.example.upcomingmovies.data.local.CachedMovie
import com.example.upcomingmovies.data.local.MovieDatabaseDao
import com.example.upcomingmovies.models.MovieResponse
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel() : ViewModel() {
    private val movieRepository=MovieRepository()
    val popularMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val topRatedMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val upcomingMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    private val _searchedMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    val searchedMovies: LiveData<Resource<MovieResponse>> = _searchedMovies

    val favouriteMoviesCount = liveData {
        emit(movieRepository.getMoviesCount())
    }

    val favouriteMovies = liveData {
        emit(movieRepository.getMovies())
    }

    init {
        fetchUpcomingMovies()
        fetchPopularMovies()
        fetchTopRatedMovies()
    }

    fun searchMovies(query: String) = viewModelScope.launch {
        _searchedMovies.postValue(Resource.Loading())
        val response = movieRepository.searchMovies(query)
        _searchedMovies.postValue(handleMoviesResponse(response))
    }

    suspend fun getFavourites(): List<CachedMovie>? = movieRepository.getMovies()


    private fun fetchPopularMovies() = viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchPopularMovies()
        popularMovies.postValue(handleMoviesResponse(response))
    }

    private fun fetchTopRatedMovies() = viewModelScope.launch {
        topRatedMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchTopRatedMovies()
        topRatedMovies.postValue(handleMoviesResponse(response))
    }

    private fun fetchUpcomingMovies() = viewModelScope.launch {
        upcomingMovies.postValue(Resource.Loading())
        val response = movieRepository.fetchUpcomingMovies()
        upcomingMovies.postValue(handleMoviesResponse(response))
    }

    private fun handleMoviesResponse(response: Response<MovieResponse>): Resource<MovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}
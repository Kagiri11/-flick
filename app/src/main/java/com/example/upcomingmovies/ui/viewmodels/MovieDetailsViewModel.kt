package com.example.upcomingmovies.ui.viewmodels

import androidx.lifecycle.*
import com.example.data.local.CachedMovie
import com.example.data.remote.dto.CastDetails
import com.example.data.remote.dto.MovieDetailsDto
import com.example.data.remote.dto.MovieResponseDto
import com.example.data.repository.MovieRepositoryImpl
import com.example.domain.utils.Resource
import com.example.upcomingmovies.models.*
import kotlinx.coroutines.launch

class MovieDetailsViewModel(

) : ViewModel() {
    private val movieRepository= MovieRepositoryImpl()
    private val _movieDetails = MutableLiveData<Resource<MovieDetailsDto>>()
    val movieDetails: LiveData<Resource<MovieDetailsDto>> get() = _movieDetails

    private val _cast = MutableLiveData<Resource<CastDetails>>()
    val cast: LiveData<Resource<CastDetails>> get() = _cast

    private val _reviews = MutableLiveData<Resource<ReviewResponse>>()
    val reviews: LiveData<Resource<ReviewResponse>> get() = _reviews

    private val _similarMovies = MutableLiveData<Resource<MovieResponseDto>>()
    val similarMovies : LiveData<Resource<MovieResponseDto>> get() = _similarMovies

    fun getMovieReviews(movieId: Int)=viewModelScope.launch {
        val reviews  = movieRepository.fetchMovieReviews(movieId)
        _reviews.postValue(handleReviewsResponse(reviews))
    }

    fun getSimilarMovies(movieId: Int)=viewModelScope.launch {
        val movies =movieRepository.fetchSimilarMovies(movieId)
        _similarMovies.postValue(Resource.Success(movies))
    }


    fun getMovieDetails(movieId:Int) = viewModelScope.launch {
        val details = movieRepository.fetchMovieDetails(movieId)
        _movieDetails.postValue(handleDetailResponse(details))
    }

    fun getMovieCast(movieId: Int)=viewModelScope.launch {
        val cast = movieRepository.fetchMovieCast(movieId)
        _cast.postValue(handleCastResponse(cast))
    }

//    fun addFavourite(cachedMovie: CachedMovie){
//        viewModelScope.launch {
//            movieRepository.addFavourite(cachedMovie)
//        }
//    }


    private fun handleDetailResponse(response: Response<MovieDetailsDto>): Resource<MovieDetailsDto> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }

    private fun handleMoviesResponse(response: Response<MovieResponseDto>):Resource<MovieResponseDto>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }

    private fun handleCastResponse(response: Response<CastDetails>): Resource<CastDetails> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }

    private fun handleReviewsResponse(response: Response<ReviewResponse>): Resource<ReviewResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }
}
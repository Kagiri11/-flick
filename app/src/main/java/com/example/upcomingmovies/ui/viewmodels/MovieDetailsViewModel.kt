package com.example.upcomingmovies.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.upcomingmovies.models.CastDetails
import com.example.upcomingmovies.models.MovieDetails
import com.example.upcomingmovies.models.MovieResponse
import com.example.upcomingmovies.models.ReviewResponse
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _movieDetails = MutableLiveData<Resource<MovieDetails>>()
    val movieDetails: LiveData<Resource<MovieDetails>> get() = _movieDetails

    private val _cast = MutableLiveData<Resource<CastDetails>>()
    val cast: LiveData<Resource<CastDetails>> get() = _cast

    private val _reviews = MutableLiveData<Resource<ReviewResponse>>()
    val reviews: LiveData<Resource<ReviewResponse>> get() = _reviews

    private val _similarMovies = MutableLiveData<Resource<MovieResponse>>()
    val similarMovies : LiveData<Resource<MovieResponse>> get() = _similarMovies

    fun getMovieReviews(movieId: Int)=viewModelScope.launch {
        val reviews  = movieRepository.fetchMovieReviews(movieId)
        _reviews.postValue(handleReviewsResponse(reviews))
    }

    fun getSimilarMovies(movieId: Int)=viewModelScope.launch {
        val movies =movieRepository.fetchSimilarMovies(movieId)
        _similarMovies.postValue(handleMoviesResponse(movies))
    }


    fun getMovieDetails(movieId:Int) = viewModelScope.launch {
        val details = movieRepository.fetchMovieDetails(movieId)
        _movieDetails.postValue(handleDetailResponse(details))
    }

    fun getMovieCast(movieId: Int)=viewModelScope.launch {
        val cast = movieRepository.fetchMovieCast(movieId)
        _cast.postValue(handleCastResponse(cast))
    }


    private fun handleDetailResponse(response: Response<MovieDetails>): Resource<MovieDetails> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)

            }
        }
        return Resource.Error(response.message())
    }

    private fun handleMoviesResponse(response: Response<MovieResponse>):Resource<MovieResponse>{
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
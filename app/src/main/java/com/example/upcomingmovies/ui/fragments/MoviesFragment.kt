package com.example.upcomingmovies.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.FragmentMoviesBinding
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.ui.adapters.PopularMoviesAdapter
import com.example.upcomingmovies.ui.adapters.TopRatedMoviesAdapter
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.ui.viewmodels.ViewModelFactory

class MoviesFragment : Fragment() {
    private lateinit var viewModel : MoviesViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentMoviesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movies,container,false)
        val application = requireNotNull(this.activity).application
        val movieRepo = MovieRepository()

        viewModelFactory=ViewModelFactory(movieRepo,application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MoviesViewModel::class.java)
        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is com.example.upcomingmovies.utils.Resource.Success-> response.data?.let { movieResponse ->
                    binding.rvPopularMovies.adapter=PopularMoviesAdapter(movieResponse.results)
                }
                is com.example.upcomingmovies.utils.Resource.Error -> {

                    response.message?.let { message->
                        Log.e("MoviesFragment", "the error is $message")

                    }
                }
                is com.example.upcomingmovies.utils.Resource.Loading -> {
                    println("Loading..")
                }
            }

        })
        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is com.example.upcomingmovies.utils.Resource.Success-> response.data?.let { movieResponse ->
                    binding.rvTopRatedMovies.adapter= TopRatedMoviesAdapter(movieResponse.results)
                }
                is com.example.upcomingmovies.utils.Resource.Error -> {

                    response.message?.let { message->
                        Log.e("MoviesFragment", "the error is $message")

                    }
                }
                is com.example.upcomingmovies.utils.Resource.Loading -> {
                    println("Loading..")
                }
            }

        })

        return binding.root
    }

}
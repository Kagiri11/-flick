package com.example.upcomingmovies.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.FragmentMoviesBinding
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.ui.adapters.PopularMoviesAdapter
import com.example.upcomingmovies.ui.adapters.TopRatedMoviesAdapter
import com.example.upcomingmovies.ui.adapters.UpcomingMoviesAdapter
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.ui.viewmodels.ViewModelFactory
import com.example.upcomingmovies.utils.Resource
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    private lateinit var viewModel: MoviesViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentMoviesBinding
    private val upcomingMoviesAdapter = UpcomingMoviesAdapter()
    private val popularMoviesAdapter = PopularMoviesAdapter()
    val topRatedMoviesAdapter = TopRatedMoviesAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        val application = requireNotNull(this.activity).application
        val movieRepo = MovieRepository()
        popularMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie",it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment,bundle)
        }
        upcomingMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie",it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment,bundle)
        }
        topRatedMoviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie",it)
            }
            findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment,bundle)
        }

        viewModelFactory = ViewModelFactory(movieRepo, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MoviesViewModel::class.java)

        viewModel.popularMovies.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> response.data?.let { movieResponse ->
                    popularMoviesAdapter.differ.submitList(movieResponse.results)
                    rv_popular_movies.adapter = popularMoviesAdapter
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("MoviesFragment", "the error is $message")

                    }
                }
                else -> {
                    println("Loading..")
                }
            }

        })
        viewModel.topRatedMovies.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> response.data?.let { movieResponse ->
                    topRatedMoviesAdapter.differ.submitList(movieResponse.results)
                    rv_top_rated_movies.apply {
                        adapter = topRatedMoviesAdapter
                    }
                }
                is Resource.Error -> {

                    response.message?.let { message ->
                        Log.e("MoviesFragment", "the error is $message")
                    }
                }
                else -> {
                    println("Loading..")
                }
            }

        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, { response ->

            when (response) {
                is Resource.Success -> response.data?.let { movieResponse ->
                    upcomingMoviesAdapter.differ.submitList(movieResponse.results)
                    rv_upcoming_movies.apply {
                        adapter = upcomingMoviesAdapter
                    }
                }
                is Resource.Error -> {

                    response.message?.let { message ->
                        Log.e("MoviesFragment", "the error is $message")
                    }
                }
                else -> {
                    println("Loading..")
                }
            }

        })


        return binding.root
    }

}
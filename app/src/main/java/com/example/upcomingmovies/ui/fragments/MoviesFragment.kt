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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.FragmentMoviesBinding
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.ui.adapters.PopularMoviesAdapter
import com.example.upcomingmovies.ui.adapters.TopRatedMoviesAdapter
import com.example.upcomingmovies.ui.adapters.UpcomingMoviesAdapter
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.ui.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    private lateinit var viewModel : MoviesViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var binding: FragmentMoviesBinding
    lateinit var topRatedMoviesAdapter : TopRatedMoviesAdapter
    lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter
    lateinit var popularMoviesAdapter: PopularMoviesAdapter
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
        topRatedMoviesAdapter = TopRatedMoviesAdapter()

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
                    topRatedMoviesAdapter.differ.submitList(movieResponse.results)
                    topRatedMoviesAdapter.setOnItemClickListener {
                        val bundle = Bundle().apply {
                            putSerializable("movie",it)
                        }
                        findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment,bundle)
                    }
                    rv_top_rated_movies.apply {
                        adapter = topRatedMoviesAdapter
                    }
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

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is com.example.upcomingmovies.utils.Resource.Success-> response.data?.let { movieResponse ->
                    binding.rvUpcomingMovies.adapter= UpcomingMoviesAdapter(movieResponse.results)
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
    fun setupTopRatedMoviesRecyclerview(){
        topRatedMoviesAdapter = TopRatedMoviesAdapter()
        rv_top_rated_movies.apply {
            adapter = topRatedMoviesAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }


}
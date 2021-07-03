package com.example.upcomingmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.FragmentMovieDetailsBinding
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.ui.viewmodels.ViewModelFactory
import com.example.upcomingmovies.utils.Resource


class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel : MoviesViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding : FragmentMovieDetailsBinding
    private val args : MovieDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_movie_details,container,false)
        val application = requireNotNull(this.activity).application
        val movieRepo = MovieRepository()

        viewModelFactory=ViewModelFactory(movieRepo,application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MoviesViewModel::class.java)


        return binding.root
    }
}
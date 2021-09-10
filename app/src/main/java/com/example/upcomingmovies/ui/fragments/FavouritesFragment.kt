package com.example.upcomingmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.upcomingmovies.R
import com.example.upcomingmovies.data.mappers.CacheMapper
import com.example.upcomingmovies.databinding.FragmentFavouritesBinding
import com.example.upcomingmovies.databinding.FragmentSearchBinding
import com.example.upcomingmovies.ui.adapters.SearchAdapter
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    val viewModel:MoviesViewModel by viewModels()
    private val adapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_favourites, container, false)

        viewModel.favouriteMovies.observe(viewLifecycleOwner,{
            val favouriteMovies = it?.let { it1 -> CacheMapper().mapToDomainList(it1) }
            adapter.differ.submitList(favouriteMovies)
            binding.rvFavourites.adapter= adapter
        })

        binding.tvTitle.text= viewModel.favouriteMoviesCount.toString()

        return binding.root
    }
}
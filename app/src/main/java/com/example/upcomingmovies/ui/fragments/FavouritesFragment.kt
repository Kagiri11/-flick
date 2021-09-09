package com.example.upcomingmovies.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.FragmentFavouritesBinding
import com.example.upcomingmovies.databinding.FragmentSearchBinding
import com.example.upcomingmovies.ui.adapters.SearchAdapter
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.ui.viewmodels.ViewModelFactory

class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private val adapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_favourites, container, false)

//        viewModel.favouriteMovies.observe(viewLifecycleOwner,{
//            adapter.differ.submitList(it)
//            binding.rvFavourites.adapter= adapter
//        })

        return binding.root
    }
}
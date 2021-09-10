package com.example.upcomingmovies.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.FragmentSearchBinding
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.ui.adapters.SearchAdapter
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.utils.Resource
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val viewModel : MoviesViewModel by viewModels()
    private val adapter = SearchAdapter()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("movie", it)
            }
            findNavController().navigate(R.id.action_searchFragment_to_movieDetailsFragment, bundle)
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        var job: Job? = null
        binding.searchView.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                editable.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchMovies(editable.toString())
                    }
                }
            }
        }

        viewModel.searchedMovies.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data.let { response ->
                        adapter.differ.submitList(response?.results)
                    }
                    rv_searched_movies.adapter = adapter
                }
                is Resource.Loading -> {
                    binding.tvSearchDesc.text = "Your search is happening"
                }
                else -> {
                    resource.message.let { errorMessage ->
                        Log.e("SearchFragment", "This error occured $errorMessage")
                    }
                }
            }

        })



        return binding.root
    }

}
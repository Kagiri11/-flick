package com.example.upcomingmovies.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.FragmentMovieDetailsBinding
import com.example.upcomingmovies.repository.MovieRepository
import com.example.upcomingmovies.ui.adapters.CastAdapter
import com.example.upcomingmovies.ui.adapters.ReviewsAdapter
import com.example.upcomingmovies.ui.adapters.SimilarMoviesAdapter
import com.example.upcomingmovies.ui.adapters.TopRatedMoviesAdapter
import com.example.upcomingmovies.ui.viewmodels.MovieDetailsViewModel
import com.example.upcomingmovies.ui.viewmodels.MovieDetailsViewModelFactory
import com.example.upcomingmovies.ui.viewmodels.MoviesViewModel
import com.example.upcomingmovies.ui.viewmodels.ViewModelFactory
import com.example.upcomingmovies.utils.Resource
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetailsFragment : Fragment() {
    private lateinit var moviesViewModel : MoviesViewModel
    lateinit var detailsViewModel: MovieDetailsViewModel
    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var movieDetailsViewModelFactory: MovieDetailsViewModelFactory
    private val castAdapter = CastAdapter()
    private val similarMoviesAdapter = SimilarMoviesAdapter()
    private val reviewsAdapter = ReviewsAdapter()
    lateinit var binding : FragmentMovieDetailsBinding
    private val args :  MovieDetailsFragmentArgs by navArgs()
    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_movie_details,container,false)
        val application = requireNotNull(this.activity).application
        val movieRepo = MovieRepository()
        val movie = args.movie

        viewModelFactory=ViewModelFactory(movieRepo,application)
        movieDetailsViewModelFactory=MovieDetailsViewModelFactory(movieRepo,application)
        moviesViewModel = ViewModelProvider(this,viewModelFactory).get(MoviesViewModel::class.java)
        detailsViewModel= ViewModelProvider(this,movieDetailsViewModelFactory).get(MovieDetailsViewModel::class.java)
        val configuration = "https://image.tmdb.org/t/p/w500"
//        binding.tvSpecificMovieName.text =movie.title
        binding.tvSpecificMovieYear.text = "Year: "+movie.release_date.take(4)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.favourite.setOnClickListener {
//            detailsViewModel.addFavourite(movie)
            Toast.makeText(this.requireContext(),"${movie.title} added to favourites",Toast.LENGTH_LONG).show()
        }

        detailsViewModel.apply {
            getMovieDetails(movie.id)
            getMovieCast(movie.id)
            getMovieReviews(movie.id)
            getSimilarMovies(movie.id)
        }
        detailsViewModel.movieDetails.observe(viewLifecycleOwner,{  response->
            when(response){
                is Resource.Success-> response.data?.let { movieDetails ->
                    binding.tvSpecificMovieName.text = movieDetails.title
                    binding.tvMovieSynopsis.text = "${movieDetails.overview.take(200)}  ..read more"
                    binding.tvSpecificMovieRating.text= movieDetails.vote_average.toString()
                    val configuration = "https://image.tmdb.org/t/p/w500"
                    val movieImage = configuration+movieDetails.backdrop_path
                    Glide.with(this).load(movieImage).into(iv_specific_movie_image)
                }
                is Resource.Error ->{
                    response.message?.let { message ->
                        Log.e("MoviesFragment", "the error is $message")

                    }
                }
                else-> println("Loading...")
            }

        })

        detailsViewModel.cast.observe(viewLifecycleOwner,{  response->
            when(response){
                is Resource.Success-> response.data?.let { castDetails ->
                    binding.tvSpecificMovieDirector.text =  "Dir: "+castDetails.crew[1].name
                    castAdapter.differ.submitList(castDetails.cast)
                    binding.rvMovieCast.adapter=castAdapter
                }
                is Resource.Error ->{
                    response.message?.let { message ->
                        Log.e("MoviesFragment", "the error is $message")

                    }
                }
                else-> println("Loading...")
            }

        })

        detailsViewModel.similarMovies.observe(viewLifecycleOwner,{  response->
            when(response){
                is Resource.Success-> response.data?.let { movieResponse ->
                    similarMoviesAdapter.differ.submitList(movieResponse.results)
                    binding.rvSimilarMovies.adapter=similarMoviesAdapter
                }
                is Resource.Error ->{
                    response.message?.let { message ->
                        Log.e("MoviesFragment", "the error is $message")

                    }
                }
                else-> println("Loading...")
            }

        })

        detailsViewModel.reviews.observe(viewLifecycleOwner,{  response->
            when(response){
                is Resource.Success-> response.data?.let { reviewResponse ->
                    if(reviewResponse.reviews.isNullOrEmpty()) {
                        binding.tvReviews.visibility=GONE
                        binding.rvReviews.visibility= GONE
                    }

                }
                is Resource.Error ->{
                    response.message?.let { message ->
                        Log.e("MoviesFragment", "the error is $message")

                    }
                }
                else-> println("Loading...")
            }

        })


        return binding.root
    }

}
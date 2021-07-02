package com.example.upcomingmovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.ItemMovieBinding
import com.example.upcomingmovies.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class PopularMoviesAdapter(private val movies : List<Movie>): RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>(){
    class PopularMoviesViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMovieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie,parent,false)
        return  PopularMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = movies[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration+movie.poster_path
        holder.itemView.apply {
            tv_movie_name.text =movie.title
            tv_movie_rating.text=movie.vote_average.toString()
            Glide.with(this).load(movieImage).into(this.ivMovieImage)
        }
    }

    override fun getItemCount(): Int = movies.size
}
package com.example.upcomingmovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.ItemTopratedMoviesBinding
import com.example.upcomingmovies.models.Movie
import kotlinx.android.synthetic.main.item_toprated_movies.view.*

class TopRatedMoviesAdapter(private val movies: List<Movie>): RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {
    class TopRatedMoviesViewHolder(binding: ItemTopratedMoviesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemTopratedMoviesBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_toprated_movies,parent,false)
        return TopRatedMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        val movie = movies[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration+movie.poster_path
        holder.itemView.apply {
            tv_top_rated_movie_name.text = movie.title
            tv_top_rated_movie_genres.text = "${movie.genre_ids}"
            Glide.with(this).load(movieImage).into(iv_top_rated_movie)
        }
    }

    override fun getItemCount(): Int =movies.size
}
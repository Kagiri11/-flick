package com.example.upcomingmovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.ItemUpcomingMoviesBinding
import com.example.upcomingmovies.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_upcoming_movies.view.*

class UpcomingMoviesAdapter(private val movies : List<Movie>): RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>(){
    class UpcomingMoviesViewHolder(binding: ItemUpcomingMoviesBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UpcomingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemUpcomingMoviesBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_upcoming_movies,parent,false)
        return  UpcomingMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        val movie = movies[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration+movie.poster_path
        holder.itemView.apply {
            tv_upcoming_movie_name.text = movie.title
            tv_upcoming_movie_genres.text = "${movie.genre_ids}"
            Glide.with(this).load(movieImage).into(iv_upcoming_movie)
        }
    }

    override fun getItemCount(): Int = movies.size
}
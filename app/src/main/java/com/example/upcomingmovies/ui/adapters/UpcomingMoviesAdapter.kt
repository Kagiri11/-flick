package com.example.upcomingmovies.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.ItemTopratedMoviesBinding
import com.example.upcomingmovies.databinding.ItemUpcomingMoviesBinding
import com.example.upcomingmovies.models.Movie
import kotlinx.android.synthetic.main.item_toprated_movies.view.*
import kotlinx.android.synthetic.main.item_upcoming_movies.view.*

class UpcomingMoviesAdapter: RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingMoviesViewHolder>() {
    class UpcomingMoviesViewHolder(binding: ItemUpcomingMoviesBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemUpcomingMoviesBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_upcoming_movies,parent,false)
        return UpcomingMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration+movie.poster_path
        holder.itemView.apply {
            tv_upcoming_movie_name.text=movie.title
            tv_upcoming_movie_genres.text = "${movie.genre_ids}"
            Glide.with(this).load(movieImage).into(iv_upcoming_movie)
            setOnClickListener {
                onItemClickListener?.let {
                   it(movie)
                }
            }
        }
    }

    private var onItemClickListener: ((Movie)->Unit)?=null

    fun setOnItemClickListener(listener:(Movie)->Unit){
        onItemClickListener=listener
    }

    override fun getItemCount(): Int = differ.currentList.size


}
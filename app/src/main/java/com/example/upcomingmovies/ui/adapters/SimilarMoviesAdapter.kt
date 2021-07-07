package com.example.upcomingmovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upcomingmovies.R
import com.example.upcomingmovies.databinding.ItemSimilarMoviesBinding
import com.example.upcomingmovies.models.Movie
import kotlinx.android.synthetic.main.item_similar_movies.view.*

class SimilarMoviesAdapter : RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMoviesViewHolder>() {
    class SimilarMoviesViewHolder(val binding : ItemSimilarMoviesBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        val layoutInflater  = LayoutInflater.from(parent.context)
        val binding: ItemSimilarMoviesBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_similar_movies,parent,false)
        return SimilarMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration+movie.poster_path
        holder.itemView.apply {
            tv_similar_movie_name.text=movie.title
            tv_similar_movies_rating.text = movie.vote_average.toString()
            Glide.with(this).load(movieImage).into(iv_similar_movie)

        }
    }

    override fun getItemCount(): Int =differ.currentList.size


}
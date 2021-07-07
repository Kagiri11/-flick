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
import com.example.upcomingmovies.databinding.ItemMovieBinding
import com.example.upcomingmovies.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class PopularMoviesAdapter: RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>(){
    class PopularMoviesViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)
    private val differCallback = object: DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMovieBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie,parent,false)
        return  PopularMoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        val movie = differ.currentList[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val movieImage = configuration+movie.poster_path
        holder.itemView.apply {
            tv_movie_name.text =movie.title
            tv_movie_rating.text=movie.vote_average.toString()
            Glide.with(this).load(movieImage).into(this.ivMovieImage)
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
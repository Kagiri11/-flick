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
import com.example.upcomingmovies.databinding.ItemMovieCastBinding
import com.example.upcomingmovies.models.Cast
import com.example.upcomingmovies.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie_cast.view.*
import kotlinx.android.synthetic.main.item_movie_cast.view.iv_cast_image
import kotlinx.android.synthetic.main.item_movie_review.view.*

class   CastAdapter: RecyclerView.Adapter<CastAdapter.CastViewHolder>(){
    class CastViewHolder(private val binding: ItemMovieCastBinding): RecyclerView.ViewHolder(binding.root)
    private val differCallback = object: DiffUtil.ItemCallback<Cast>(){
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemMovieCastBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_movie_cast,parent,false)
        return  CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val cast = differ.currentList[position]
        val configuration = "https://image.tmdb.org/t/p/w500"
        val sconfiguration = "https://image.tmdb.org/t/p/w500/cIi5pCTdVpD4Hn0OEcJhJeNKYMw.jpg"
        val castImage = configuration+cast.profile_path
        holder.itemView.apply {
            tv_cast_name.text=cast.name
            Glide.with(this).load(castImage).circleCrop().into(iv_cast_image)

        }
    }

    override fun getItemCount(): Int = differ.currentList.size


}
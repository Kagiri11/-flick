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
import com.example.upcomingmovies.databinding.ItemMovieReviewBinding
import com.example.upcomingmovies.models.Cast
import com.example.upcomingmovies.models.Movie
import com.example.upcomingmovies.models.Review
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_movie_cast.view.*
import kotlinx.android.synthetic.main.item_movie_cast.view.iv_cast_image
import kotlinx.android.synthetic.main.item_movie_review.view.*

class ReviewsAdapter: RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>(){
    class ReviewViewHolder(val binding: ItemMovieReviewBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallback =  object: DiffUtil.ItemCallback<Review>(){
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieReviewBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_movie_review,parent,false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review= differ.currentList[position]
        holder.itemView.apply {
            tv_review.text=review.content
            tv_review_author_name.text=review.author_details.name
            tv_review_rating.text=review.author_details.rating.toString()
        }
    }

    override fun getItemCount(): Int =differ.currentList.size
}
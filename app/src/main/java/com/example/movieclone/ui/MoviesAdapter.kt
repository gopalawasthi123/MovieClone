package com.example.movieclone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.movieclone.data.Constants
import com.example.movieclone.data.MovieList
import com.example.movieclone.databinding.MovieItemBinding

class MoviesAdapter : ListAdapter<MovieList, MoviesAdapter.MoviesViewHolder>(MOVIES_COMPARATOR) {

    class MoviesViewHolder(private val itemViewBinding: MovieItemBinding) : RecyclerView.ViewHolder(itemViewBinding.root){

        fun binds(movie : MovieList?){
            Glide.with(itemViewBinding.root).load(Constants.BASE_IMAGE_URL
                    + movie?.poster_path).into(itemViewBinding.moviePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {

        val viewBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MoviesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        var item = getItem(position)
        holder.binds(item)
    }


    companion object{
        val MOVIES_COMPARATOR = object : DiffUtil.ItemCallback<MovieList>() {

            override fun areItemsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
                return oldItem == newItem
            }

        }
    }

}
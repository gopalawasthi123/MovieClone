package com.example.movieclone.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieclone.data.Constants
import com.example.movieclone.data.MovieList
import com.example.movieclone.databinding.FullMovieItemBinding
import com.example.movieclone.databinding.FullmovieFragmentBinding

class ShowAllMoviesAdapter : PagingDataAdapter<MovieList,ShowAllMoviesAdapter.AllMoviesViewHolder>(DIFF_UTIL_MOVIES) {

    class AllMoviesViewHolder(private val binding: FullMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun binds(movie : MovieList?){
            Glide.with(binding.root).load(
                Constants.BASE_IMAGE_URL
                    + movie?.poster_path).into(binding.moviePoster)
            binding.movieTxt.text = movie?.title
        }
    }


    companion object{
        val DIFF_UTIL_MOVIES = object  : DiffUtil.ItemCallback<MovieList>(){

            override fun areItemsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieList, newItem: MovieList): Boolean {
                return  oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: AllMoviesViewHolder, position: Int) {
        val item = getItem(position)
        holder.binds(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllMoviesViewHolder {

       val binding = FullMovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val  view =binding.root

        return AllMoviesViewHolder(binding)
    }


}
package com.example.movieclone.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieclone.data.MovieList

class MoviesAdapter : ListAdapter<MovieList, MoviesAdapter.MoviesViewHolder>(MOVIES_COMPARATOR) {

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        TODO("Not yet implemented")
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
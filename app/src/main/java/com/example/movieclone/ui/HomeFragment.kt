package com.example.movieclone.ui

import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieclone.R
import com.example.movieclone.data.MovieList
import com.example.movieclone.databinding.FragmentHomeBinding
import com.example.movieclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewmodel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val homeBinding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        val view = homeBinding.root
        val moviesAdapter = MoviesAdapter()

        homeBinding.recyclerVIew.adapter = moviesAdapter
        homeBinding.recyclerVIew.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)


        viewmodel.movieList.observe(viewLifecycleOwner) {
            movies -> movies?.let { moviesAdapter.submitList(it) }
        }

        lifecycleScope.launchWhenStarted {
            viewmodel.getMoviesListFromRepository()
        }

        return view
    }


}
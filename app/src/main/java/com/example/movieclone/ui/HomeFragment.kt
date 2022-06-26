package com.example.movieclone.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieclone.R
import com.example.movieclone.databinding.FragmentHomeBinding
import com.example.movieclone.ui.adapters.MoviesAdapter
import com.example.movieclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    companion object{
        const val AllMoviesFragmentTag ="AllMoviesFragment"
        const val HomeFragmentTag = "HomeFragment"
    }

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


        homeBinding.showAllPopularMovies.setOnClickListener{
            val fragment = AllMoviesFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView,fragment)?.addToBackStack(null)?.commit()
        }

        homeBinding.fab.setOnClickListener{
            val searchFragment = SearchFragment()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentContainerView,searchFragment)
                ?.addToBackStack(null)?.commit()
        }

        viewmodel.movieList.observe(viewLifecycleOwner) {
            movies -> movies?.let { moviesAdapter.submitList(it) }
        }

        lifecycleScope.launchWhenStarted {
            Log.d("Gopal","get movies from ViewModel")
            viewmodel.getMoviesListFromRepository()
        }
        return view

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d("Gopal","on config changed is called")
    }


}
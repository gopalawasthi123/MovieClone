package com.example.movieclone.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieclone.databinding.FullmovieFragmentBinding
import com.example.movieclone.ui.adapters.MoviesAdapter
import com.example.movieclone.ui.adapters.ShowAllMoviesAdapter
import com.example.movieclone.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllMoviesFragment : Fragment(){

    private val viewmodel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = FullmovieFragmentBinding.inflate(layoutInflater,container,false)

        val adapter = ShowAllMoviesAdapter()

        binding.recyclerFullMovie.adapter = adapter
        binding.recyclerFullMovie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val items = viewmodel.fullMovieList
         viewLifecycleOwner.lifecycleScope.launch{
             viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                items.collectLatest { adapter.submitData(it) }
            }
        }

        return binding.root
    }
}
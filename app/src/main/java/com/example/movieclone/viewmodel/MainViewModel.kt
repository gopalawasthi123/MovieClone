package com.example.movieclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieclone.data.MovieList
import com.example.movieclone.data.Movies
import com.example.movieclone.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<List<MovieList>>()

    val movieList =  _moviesList

    suspend fun getMoviesListFromRepository() {
       movieRepository.getPopularMovies()?.let {
           _moviesList.postValue(it)
       }

    }
}
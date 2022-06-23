package com.example.movieclone.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieclone.data.MovieList
import com.example.movieclone.data.Movies
import com.example.movieclone.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<List<MovieList>>()

    val movieList  = _moviesList as LiveData<List<MovieList>>

    suspend fun getMoviesListFromRepository() {
        movieRepository.getPopularMovies()?.let {
            _moviesList.postValue(it)
        }
    }

    val fullMovieList = movieRepository.getAllMovies().cachedIn(viewModelScope)


     fun getSearchMovies(query: String): kotlinx.coroutines.flow.Flow<PagingData<MovieList>> {
       return movieRepository.getSearchMovies(query)
    }
}
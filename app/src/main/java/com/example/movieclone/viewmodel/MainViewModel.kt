package com.example.movieclone.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieclone.data.ItemClass
import com.example.movieclone.data.MovieList
import com.example.movieclone.data.Movies
import com.example.movieclone.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _moviesList = MutableLiveData<List<MovieList>>()

    val movieList : LiveData<List<MovieList>>  = _moviesList

    suspend fun getMoviesListFromRepository() {
        movieRepository.getPopularMovies()?.let {
            _moviesList.postValue(it)
        }
    }

    private val _movieState = MutableStateFlow<PagingData<MovieList>>(PagingData.empty())

    val movieState = _movieState.asStateFlow()

    val fullMovieList = movieRepository.getAllMovies(ItemClass.ALL_MOVIES).cachedIn(viewModelScope)


     fun getSearchMovies(query: String) : StateFlow<PagingData<MovieList>>{
         Log.d("Gopal","getSearchMovies is called")
       return movieRepository.getSearchMovies(ItemClass.MOVIE_SEARCH,query).stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),
           PagingData.empty())
    }
}
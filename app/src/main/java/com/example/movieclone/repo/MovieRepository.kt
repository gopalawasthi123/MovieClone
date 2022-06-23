package com.example.movieclone.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movieclone.data.Constants
import com.example.movieclone.data.MovieList
import com.example.movieclone.di.MovieRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject


class MovieRepository @Inject constructor(private val movieRepo: MovieRepo) {


    suspend fun getPopularMovies() : List<MovieList>? {
        movieRepo.getPopularMovies(Constants.STARTING_PAGE).let {
            if(it.isSuccessful){
             return it.body()?.results
            }
        }
        return null
    }

    fun getAllMovies()  : Flow<PagingData<MovieList>>{
        return  Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {MoviePagingSource(movieRepo,"")}
        ).flow
    }

    fun getSearchMovies(query: String) : Flow<PagingData<MovieList>>{
        return  Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {MoviePagingSource(movieRepo,query)}
        ).flow
    }

}
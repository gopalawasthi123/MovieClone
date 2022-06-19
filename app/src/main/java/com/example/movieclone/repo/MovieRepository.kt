package com.example.movieclone.repo

import com.example.movieclone.data.MovieList
import com.example.movieclone.di.MovieRepo
import javax.inject.Inject


class MovieRepository @Inject constructor(private val movieRepo: MovieRepo) {

    suspend fun getPopularMovies() : List<MovieList>? {
        movieRepo.getPopularMovies().let {
            if(it.isSuccessful){
             return it.body()?.results
            }
        }
        return null
    }

}
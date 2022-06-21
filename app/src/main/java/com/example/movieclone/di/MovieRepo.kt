package com.example.movieclone.di

import com.example.movieclone.data.Movies
import retrofit2.Response
import retrofit2.http.GET

interface MovieRepo {

  @GET("popular/")
  suspend fun getPopularMovies() : Response<Movies>


}
package com.example.movieclone.di

import com.example.movieclone.data.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRepo {

  @GET("popular/")
  suspend fun getPopularMovies(@Query("page")page : Int) : Response<Movies>


}
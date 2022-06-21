package com.example.movieclone.di

import com.example.movieclone.data.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRepo {

  @GET("movie/popular/")
  suspend fun getPopularMovies(@Query("page")page : Int) : Response<Movies>

  @GET("search/movie")
  suspend fun searchMovies(@Query("query") query: String) : Response<Movies>
}
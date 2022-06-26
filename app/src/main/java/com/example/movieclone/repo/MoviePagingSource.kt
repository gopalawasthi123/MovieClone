package com.example.movieclone.repo

import android.content.ClipData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieclone.data.Constants.Companion.ENDING_PAGE
import com.example.movieclone.data.Constants.Companion.STARTING_PAGE
import com.example.movieclone.data.ItemClass
import com.example.movieclone.data.MovieList
import com.example.movieclone.data.Movies
import com.example.movieclone.di.MovieRepo
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException



class MoviePagingSource(private val movieRepository: MovieRepo, private val itemClass: ItemClass,private val query : String = "") :
    PagingSource<Int, MovieList>() {

    override fun getRefreshKey(state: PagingState<Int, MovieList>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieList> {

        val page = params.key ?: STARTING_PAGE

       return  try {

           when (itemClass) {
               ItemClass.ALL_MOVIES -> {
                   val response =movieRepository.getPopularMovies(page)
                   LoadResult.Page(
                       prevKey = if(page == STARTING_PAGE) null else page-1,
                       nextKey = if(page == ENDING_PAGE) null else page +1,
                       data = response.body()?.results!!
                   )
               }
               ItemClass.MOVIE_SEARCH ->{
                   val response = movieRepository.searchMovies(query,page)
                   LoadResult.Page(
                       prevKey = if(page == STARTING_PAGE) null else page-1,
                       nextKey = if(page == ENDING_PAGE) null else page +1,
                       data = response.body()?.results!!
                   )
               }
           }

        }
        catch (exception : IOException){
            LoadResult.Error(exception)
        }
        catch (exception : HttpException){
            LoadResult.Error(exception)
        }
    }
}
package com.example.movieclone.data

data class Movies(
    val page: Int?,
    val results: List<MovieList>?,
    val total_pages: Int?,
    val total_results: Int?
)
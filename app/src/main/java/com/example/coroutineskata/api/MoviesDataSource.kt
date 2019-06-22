package com.example.coroutineskata.api

import androidx.lifecycle.LiveData
import com.example.coroutineskata.model.MovieDetailResponse

interface MoviesDataSource {

    val downloadMovieInfo : LiveData<MovieDetailResponse>

    suspend fun fetchCurrentMovie(
        movieId : String,
        language : String
    )
}
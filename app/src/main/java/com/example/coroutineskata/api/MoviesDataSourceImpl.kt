package com.example.coroutineskata.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.coroutineskata.internal.Exceptions.NoConnectivityException
import com.example.coroutineskata.model.MovieDetailResponse

class MoviesDataSourceImpl(private val apiService : MoviesClient) : MoviesDataSource {

    private val _downloadMovieInfo = MutableLiveData<MovieDetailResponse>()

    override val downloadMovieInfo: LiveData<MovieDetailResponse>
        get() = _downloadMovieInfo
    override suspend fun fetchCurrentMovie(movieId: String, language: String) {
        try {
            val currentMovie =
                apiService.getMoviewById("320288","es-MX")
                    .await()
            _downloadMovieInfo.postValue(currentMovie)
        }catch (error : NoConnectivityException){
            error.printStackTrace()
        }
    }
}
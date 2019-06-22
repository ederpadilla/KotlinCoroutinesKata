package com.example.coroutineskata.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coroutineskata.model.CURRENT_ID
import com.example.coroutineskata.model.MovieDetailResponse
import com.example.coroutineskata.model.MovieEntry

@Dao
interface SpecificMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(movie : MovieDetailResponse)

    @Query("select * from movie_detail where imdbId = $CURRENT_ID")
    fun getMovie(): LiveData<MovieEntry>



}
package com.example.coroutineskata.model

import androidx.room.ColumnInfo


data class MovieEntry (
    @ColumnInfo(name = "originalLanguage")
    override val originalLanguage: String,
    @ColumnInfo(name = "title")
    override val title: String,
    @ColumnInfo(name = "popularity")
    override val popularity: Double,
    @ColumnInfo(name = "overview")
    override val overview: String
):SpecificMovieEntry
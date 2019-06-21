package com.example.coroutineskata.model

import com.google.gson.annotations.SerializedName

data class ResultsItem(
	val overview: String,
	@SerializedName("original_language")
	val originalLanguage: String,
	@SerializedName("original_title")
	val originalTitle: String,
	val video: Boolean,
	val title: String,
	@SerializedName("genre_ids")
	val genreIds: List<Int>,
	@SerializedName("poster_path")
	val posterPath: String,
	@SerializedName("backdrop_path")
	val backdropPath: String,
	@SerializedName("release_date")
	val releaseDate: String,
	@SerializedName("vote_average")
	val voteAverage: Double,
	val popularity: Double,
	val id: Int,
	val adult: Boolean,
	@SerializedName("vote_count")
	val voteCount: Int
)

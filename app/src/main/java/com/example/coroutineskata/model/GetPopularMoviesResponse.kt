package com.example.coroutineskata.model

import com.google.gson.annotations.SerializedName

data class GetPopularMoviesResponse(
	@SerializedName("total_pages")
	val totalPages: Int,
	val results: MutableList<ResultsItem>,
	val page: Int,
	@SerializedName("total_results")
	val totalResults: Int
)

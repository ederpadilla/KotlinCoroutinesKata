package com.example.coroutineskata.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

const val CURRENT_ID = 0

@Entity(tableName = "movie_detail")
data class MovieDetailResponse(

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("imdb_id")
	val imdbId: String,

	@field:SerializedName("video")
	val video: Boolean,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("revenue")
	val revenue: Int,

/*	@field:SerializedName("genres")
	val genres: List<GenresItem>,*/

	@field:SerializedName("popularity")
	val popularity: Double,

/*	@field:SerializedName("production_countries")
	val productionCountries: List<ProductionCountriesItem?>,*/

	@field:SerializedName("vote_count")
	val voteCount: Int,

	@field:SerializedName("budget")
	val budget: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("runtime")
	val runtime: Int,

	@field:SerializedName("poster_path")
	val posterPath: String,

/*	@field:SerializedName("spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItem?>,

	@field:SerializedName("production_companies")
	@TypeConverters(ProductionCompaniesItemTypeConverters::class)
	val productionCompanies: List<ProductionCompaniesItem?>,*/

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("belongs_to_collection")
	val belongsToCollection: Boolean,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("homepage")
	val homepage: String,

	@field:SerializedName("status")
	val status: String,

	@PrimaryKey(autoGenerate = false)
	var id : Int = CURRENT_ID


)
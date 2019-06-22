package com.example.coroutineskata.api

import com.example.coroutineskata.model.GetPopularMoviesResponse
import com.example.coroutineskata.model.MovieDetailResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesClient {

    @GET("movie/{movieId}")
    fun getMoviewById(
        @Path("movieId") movieId : String,
        @Query("language") language : String
    ) : Deferred<MovieDetailResponse>

    companion object {
        operator fun invoke(
            connectivityIntercpetor: ConnectivityIntercpetor
        ) : MoviesClient{
            val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val requestInterceptor = Interceptor{chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key","fa6b37b13de2f745ef7038788f816a41")
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(logging)
                .addInterceptor(connectivityIntercpetor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesClient::class.java)
        }
    }
}
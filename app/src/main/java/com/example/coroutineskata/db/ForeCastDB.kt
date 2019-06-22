package com.example.coroutineskata.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coroutineskata.model.GenresItemsConverters
import com.example.coroutineskata.model.MovieDetailResponse
import com.example.coroutineskata.model.ProductionCountriesConverters

@Database(
    entities = [MovieDetailResponse::class],
    version = 1
)
@TypeConverters(GenresItemsConverters::class, ProductionCountriesConverters::class)
abstract class ForeCastDB : RoomDatabase() {

    abstract fun movieDao() : SpecificMovieDao

    companion object{
        @Volatile private var instance: ForeCastDB? = null
        private val LOCK = Any()
        operator fun invoke(context : Context) = instance ?: synchronized(LOCK){
            instance?: buildDatabase(context).also {
                instance=it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,ForeCastDB::class.java,"movie.db")
                .build()
    }
}
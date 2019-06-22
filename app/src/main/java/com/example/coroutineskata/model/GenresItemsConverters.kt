package com.example.coroutineskata.model

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class GenresItemsConverters {


    companion object{
        @JvmStatic
        @TypeConverter
        fun fromString(value: String): List<GenresItem> {
            val listType = object : TypeToken<ArrayList<String>>() {

            }.type
            return Gson().fromJson(value, listType)
        }

        @JvmStatic
        @TypeConverter
        fun fromArrayList(list: List<GenresItem>): String {
            val gson = Gson()
            return gson.toJson(list)
        }

    }
}

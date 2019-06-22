package com.example.coroutineskata.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountriesConverters {

    companion object{
        @JvmStatic
        @TypeConverter
        fun fromString(value: String): List<ProductionCountriesItem> {
            val listType = object : TypeToken<ArrayList<String>>() {

            }.type
            return Gson().fromJson(value, listType)
        }

        @JvmStatic
        @TypeConverter
        fun fromArrayList(list: List<ProductionCountriesItem>): String {
            val gson = Gson()
            return gson.toJson(list)
        }

    }


}
package com.example.coroutineskata.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import java.util.Arrays.asList



class ProductionCompaniesItemTypeConverters {


    @TypeConverter
    fun fromCountryLangList(value: List<ProductionCompaniesItem>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompaniesItem>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCountryLangList(value: String): List<ProductionCompaniesItem> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompaniesItem>>() {}.type
        return gson.fromJson(value, type)
    }
}
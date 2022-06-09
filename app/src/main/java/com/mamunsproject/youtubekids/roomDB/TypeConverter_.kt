package com.mamunsproject.youtubekids.roomDB

import androidx.room.TypeConverter
import com.example.cartoontuberefinefinal.Model.ResponseVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter_ {



    var gson = Gson()

    //Here using gson library to convert FoodRecipe Object to String
    @TypeConverter
    fun foodRecipeToString(videoResponse: ResponseVideo): String {
        return gson.toJson(videoResponse)
    }


    @TypeConverter
    fun stringToFoodRecipe(data: String): ResponseVideo {
        val listType = object : TypeToken<ResponseVideo>() {}.type
        return gson.fromJson(data, listType)
    }
}
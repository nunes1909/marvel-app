package com.example.marvelapp.data.local.converter

import androidx.room.TypeConverter
import com.example.marvelapp.data.model.thumbnail.Thumbnail
import com.google.gson.Gson

class ThumbnailConverter {

    @TypeConverter
    fun paraString(thumbnail: Thumbnail): String {
        return Gson().toJson(thumbnail)
    }

    @TypeConverter
    fun paraThumbnail(valor: String): Thumbnail {
        return Gson().fromJson(valor, Thumbnail::class.java)
    }
}

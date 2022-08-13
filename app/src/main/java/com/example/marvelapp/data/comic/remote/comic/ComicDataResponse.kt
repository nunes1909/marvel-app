package com.example.marvelapp.data.comic.remote.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ComicDataResponse(
    @SerializedName("data")
    val data: ComicDataContainer
) : Serializable

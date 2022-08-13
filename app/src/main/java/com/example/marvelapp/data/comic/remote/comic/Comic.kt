package com.example.marvelapp.data.comic.remote.comic

import com.example.marvelapp.data.thumbnail.Thumbnail
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comic(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
) : Serializable

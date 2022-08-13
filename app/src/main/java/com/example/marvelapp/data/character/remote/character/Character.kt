package com.example.marvelapp.data.character.remote.character

import com.example.marvelapp.data.thumbnail.Thumbnail
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
) : Serializable

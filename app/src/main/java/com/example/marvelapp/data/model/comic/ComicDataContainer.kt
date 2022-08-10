package com.example.marvelapp.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ComicDataContainer(
    @SerializedName("results")
    val results: List<Comic>
) : Serializable

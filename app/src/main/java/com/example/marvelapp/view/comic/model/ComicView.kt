package com.example.marvelapp.view.comic.model

import com.example.marvelapp.data.thumbnail.Thumbnail
import java.io.Serializable

data class ComicView(
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: Thumbnail
) : Serializable

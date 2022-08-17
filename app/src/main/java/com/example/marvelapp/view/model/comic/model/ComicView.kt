package com.example.marvelapp.view.model.comic.model

import com.example.marvelapp.data.model.thumbnail.Thumbnail
import java.io.Serializable

data class ComicView(
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: Thumbnail
) : Serializable

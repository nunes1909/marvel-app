package com.example.marvelapp.domain.comic.model

import com.example.marvelapp.data.thumbnail.Thumbnail
import java.io.Serializable

data class ComicDomain(
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: Thumbnail
) : Serializable

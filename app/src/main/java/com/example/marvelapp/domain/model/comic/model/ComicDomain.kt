package com.example.marvelapp.domain.model.comic.model

import com.example.marvelapp.data.model.thumbnail.Thumbnail
import java.io.Serializable

data class ComicDomain(
    val id: Int,
    val title: String,
    val description: String? = null,
    val thumbnail: Thumbnail
) : Serializable


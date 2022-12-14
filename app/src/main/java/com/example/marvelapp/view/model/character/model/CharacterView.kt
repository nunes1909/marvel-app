package com.example.marvelapp.view.model.character.model

import com.example.marvelapp.data.model.thumbnail.Thumbnail
import java.io.Serializable

data class CharacterView(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) : Serializable
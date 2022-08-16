package com.example.marvelapp.domain.character.model

import com.example.marvelapp.data.model.thumbnail.Thumbnail
import java.io.Serializable

data class CharacterDomain(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) : Serializable
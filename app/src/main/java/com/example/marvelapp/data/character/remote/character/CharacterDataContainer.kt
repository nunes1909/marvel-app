package com.example.marvelapp.data.character.remote.character

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterDataContainer(
    @SerializedName("results")
    val results: List<Character>
) : Serializable
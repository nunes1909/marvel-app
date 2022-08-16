package com.example.marvelapp.data.model.character.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterDataResponse(
    @SerializedName("data")
    val data: CharacterDataContainer
) : Serializable


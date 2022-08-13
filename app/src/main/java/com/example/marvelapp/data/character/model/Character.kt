package com.example.marvelapp.data.character.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelapp.data.thumbnail.Thumbnail
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CHARACTER")
data class Character(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
) : Serializable

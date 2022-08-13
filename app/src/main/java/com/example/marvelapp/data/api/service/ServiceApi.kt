package com.example.marvelapp.data.api.service

import com.example.marvelapp.data.character.model.CharacterDataResponse
import com.example.marvelapp.data.comic.remote.comic.ComicDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("characters")
    suspend fun list(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ) : Response<CharacterDataResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path(value = "characterId", encoded = true) characterId: Int
    ) : Response<ComicDataResponse>
}
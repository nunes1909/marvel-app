package com.example.marvelapp.domain.model.comic.repository

import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.util.state.ResourceState

interface ComicRepository {
    suspend fun getComic(characterId: Int): ResourceState<List<ComicDomain>>
}

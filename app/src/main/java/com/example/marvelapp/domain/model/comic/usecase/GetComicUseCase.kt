package com.example.marvelapp.domain.model.comic.usecase

import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.util.state.ResourceState

interface GetComicUseCase {
    suspend operator fun invoke(characterId: Int): ResourceState<List<ComicDomain>>
}

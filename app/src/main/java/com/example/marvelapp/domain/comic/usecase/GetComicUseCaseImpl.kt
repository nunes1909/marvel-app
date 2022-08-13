package com.example.marvelapp.domain.comic.usecase

import com.example.marvelapp.domain.comic.model.ComicDomain
import com.example.marvelapp.domain.comic.repository.ComicRepository
import com.example.marvelapp.util.state.ResourceState

class GetComicUseCaseImpl(
    private val repository: ComicRepository
) : GetComicUseCase {
    override suspend fun invoke(characterId: Int): ResourceState<List<ComicDomain>> {
        return repository.getComic(characterId)
    }
}
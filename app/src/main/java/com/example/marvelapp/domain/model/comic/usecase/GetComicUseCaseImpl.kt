package com.example.marvelapp.domain.model.comic.usecase

import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.domain.model.comic.repository.ComicRepository
import com.example.marvelapp.util.state.ResourceState

class GetComicUseCaseImpl(
    private val repository: ComicRepository
) : GetComicUseCase {
    override suspend fun invoke(characterId: Int): ResourceState<List<ComicDomain>> {
        return repository.getComic(characterId)
    }
}

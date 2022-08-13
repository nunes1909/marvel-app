package com.example.marvelapp.domain.character.usecase.favoritos

import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritosUseCaseImpl(
    private val repository: CharacterRepository
): GetFavoritosUseCase {
    override suspend fun invoke(): Flow<List<CharacterDomain>> {
        return repository.buscaFavoritos()
    }
}
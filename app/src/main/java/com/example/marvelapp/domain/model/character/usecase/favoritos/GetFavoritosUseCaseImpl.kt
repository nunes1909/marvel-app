package com.example.marvelapp.domain.model.character.usecase.favoritos

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.domain.model.character.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritosUseCaseImpl(
    private val repository: CharacterRepository
): GetFavoritosUseCase {
    override suspend fun invoke(): Flow<List<CharacterDomain>> {
        return repository.buscaFavoritos()
    }
}

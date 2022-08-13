package com.example.marvelapp.domain.character.usecase.favoritos

import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.repository.CharacterRepository

class SaveFavoritosUseCaseImpl(
    private val repository: CharacterRepository
): SaveFavoritosUseCase {
    override suspend fun invoke(characterDomain: CharacterDomain) {
        return repository.salvaFavorito(characterDomain)
    }
}
package com.example.marvelapp.domain.model.character.usecase.favoritos

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.domain.model.character.repository.CharacterRepository

class SaveFavoritosUseCaseImpl(
    private val repository: CharacterRepository
): SaveFavoritosUseCase {
    override suspend fun invoke(characterDomain: CharacterDomain) {
        return repository.salvaFavorito(characterDomain)
    }
}

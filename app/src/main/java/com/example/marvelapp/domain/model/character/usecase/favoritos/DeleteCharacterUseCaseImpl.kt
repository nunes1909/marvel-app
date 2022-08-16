package com.example.marvelapp.domain.model.character.usecase.favoritos

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.domain.model.character.repository.CharacterRepository

class DeleteCharacterUseCaseImpl(
    private val repository: CharacterRepository
): DeleteCharacterUseCase {
    override suspend fun invoke(characterDomain: CharacterDomain) {
        repository.delete(characterDomain)
    }
}

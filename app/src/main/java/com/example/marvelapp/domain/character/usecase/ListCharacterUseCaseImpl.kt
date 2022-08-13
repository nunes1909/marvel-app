package com.example.marvelapp.domain.character.usecase

import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.repository.CharacterRepository

class ListCharacterUseCaseImpl(
    private val repository: CharacterRepository,
) : ListCharacterUseCase {
    override suspend fun invoke(nameStartsWith: String?): List<CharacterDomain>? {
        return repository.listOneOrAll(nameStartsWith)
    }
}
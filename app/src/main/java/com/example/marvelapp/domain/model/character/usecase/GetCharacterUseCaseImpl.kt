package com.example.marvelapp.domain.model.character.usecase

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.domain.model.character.repository.CharacterRepository
import com.example.marvelapp.util.state.ResourceState

class GetCharacterUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharacterUseCase {
    override suspend fun invoke(nameStartsWith: String?): ResourceState<List<CharacterDomain>> {
        return repository.getCharacter(nameStartsWith)
    }
}

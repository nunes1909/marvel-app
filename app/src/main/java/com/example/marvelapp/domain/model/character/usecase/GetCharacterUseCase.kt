package com.example.marvelapp.domain.model.character.usecase

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.util.state.ResourceState

interface GetCharacterUseCase {
    suspend operator fun invoke(nameStartsWith: String? = null) : ResourceState<List<CharacterDomain>>
}

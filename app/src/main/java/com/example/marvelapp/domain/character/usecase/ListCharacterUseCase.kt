package com.example.marvelapp.domain.character.usecase

import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.util.state.ResourceState

interface ListCharacterUseCase {
    suspend operator fun invoke(nameStartsWith: String? = null) : ResourceState<List<CharacterDomain>>
}
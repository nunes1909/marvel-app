package com.example.marvelapp.domain.character.usecase

import com.example.marvelapp.domain.character.model.CharacterDomain

interface ListCharacterUseCase {
    suspend operator fun invoke(nameStartsWith: String? = null) : List<CharacterDomain>?
}
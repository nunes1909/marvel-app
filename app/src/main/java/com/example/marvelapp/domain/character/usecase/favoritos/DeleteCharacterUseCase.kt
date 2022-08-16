package com.example.marvelapp.domain.character.usecase.favoritos

import com.example.marvelapp.domain.character.model.CharacterDomain

interface DeleteCharacterUseCase {
    suspend operator fun invoke(characterDomain: CharacterDomain)
}
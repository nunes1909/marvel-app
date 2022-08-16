package com.example.marvelapp.domain.model.character.usecase.favoritos

import com.example.marvelapp.domain.model.character.model.CharacterDomain

interface SaveFavoritosUseCase {
    suspend operator fun invoke(characterDomain: CharacterDomain)
}

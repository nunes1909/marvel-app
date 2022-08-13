package com.example.marvelapp.domain.character.usecase.favoritos

import com.example.marvelapp.domain.character.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface GetFavoritosUseCase {
    suspend operator fun invoke(): Flow<List<CharacterDomain>>
}
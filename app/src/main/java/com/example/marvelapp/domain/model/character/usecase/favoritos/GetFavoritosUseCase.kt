package com.example.marvelapp.domain.model.character.usecase.favoritos

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface GetFavoritosUseCase {
    suspend operator fun invoke(): Flow<List<CharacterDomain>>
}

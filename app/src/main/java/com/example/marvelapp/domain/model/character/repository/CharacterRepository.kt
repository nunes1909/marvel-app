package com.example.marvelapp.domain.model.character.repository

import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.util.state.ResourceState
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacter(nameStartsWith: String?): ResourceState<List<CharacterDomain>>

    suspend fun salvaFavorito(characterDomain: CharacterDomain)

    suspend fun buscaFavoritos(): Flow<List<CharacterDomain>>

    suspend fun delete(characterDomain: CharacterDomain)
}

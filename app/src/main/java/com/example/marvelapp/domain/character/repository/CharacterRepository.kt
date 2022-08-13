package com.example.marvelapp.domain.character.repository

import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.util.state.ResourceState

interface CharacterRepository {
    suspend fun listOneOrAll(nameStartsWith: String?): ResourceState<List<CharacterDomain>>
}
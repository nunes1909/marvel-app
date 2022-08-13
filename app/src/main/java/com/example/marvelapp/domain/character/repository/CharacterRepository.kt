package com.example.marvelapp.domain.character.repository

import com.example.marvelapp.domain.character.model.CharacterDomain

interface CharacterRepository {
    suspend fun listOneOrAll(nameStartsWith: String?): List<CharacterDomain>?
}
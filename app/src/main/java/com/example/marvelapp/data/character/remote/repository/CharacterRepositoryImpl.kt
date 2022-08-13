package com.example.marvelapp.data.character.remote.repository

import com.example.marvelapp.data.character.remote.mapper.CharacterDataMapper
import com.example.marvelapp.data.remote.service.ServiceApi
import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val api: ServiceApi,
    private val mapper: CharacterDataMapper
) : CharacterRepository {
    override suspend fun listOneOrAll(nameStartsWith: String?): List<CharacterDomain>? {
        val listCharacterResponse = api.list(nameStartsWith).body()?.data?.results

        return listCharacterResponse?.let { listData ->
            mapper.mapFromCachedNonNull(listData)
        }
    }
}
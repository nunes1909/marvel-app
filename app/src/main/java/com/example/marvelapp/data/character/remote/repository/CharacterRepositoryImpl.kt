package com.example.marvelapp.data.character.remote.repository

import com.example.marvelapp.data.character.remote.character.CharacterDataResponse
import com.example.marvelapp.data.character.remote.mapper.CharacterDataMapper
import com.example.marvelapp.data.remote.service.ServiceApi
import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.repository.CharacterRepository
import com.example.marvelapp.util.state.ResourceState
import retrofit2.Response
import java.io.IOException

class CharacterRepositoryImpl(
    private val api: ServiceApi,
    private val mapper: CharacterDataMapper
) : CharacterRepository {
    override suspend fun listOneOrAll(
        nameStartsWith: String?
    ): ResourceState<List<CharacterDomain>> {
        return try {
            val response = api.list(nameStartsWith)
            validaResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    ResourceState.Error("Erro de conexão com a internet.")
                }
                else -> {
                    ResourceState.Error("Falha na conversão dos dados.")
                }
            }
        }
    }

    private fun validaResponse(response: Response<CharacterDataResponse>): ResourceState<List<CharacterDomain>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val listDomain = mapperData(values)
                return ResourceState.Success(listDomain)
            }
        }
        return ResourceState.Error(response.message())

    }

    private fun mapperData(values: CharacterDataResponse): List<CharacterDomain> {
        val results = values.data.results
        return mapper.mapFromCachedNonNull(results)
    }


}
package com.example.marvelapp.data.character.remote.repository

import android.content.Context
import com.example.marvelapp.R
import com.example.marvelapp.data.character.remote.character.CharacterDataResponse
import com.example.marvelapp.data.character.remote.mapper.CharacterDataMapper
import com.example.marvelapp.data.api.service.ServiceApi
import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.repository.CharacterRepository
import com.example.marvelapp.util.state.ResourceState
import retrofit2.Response
import java.io.IOException

class CharacterRepositoryImpl(
    private val api: ServiceApi,
    private val mapper: CharacterDataMapper,
    private val context: Context
) : CharacterRepository {
    override suspend fun getCharacter(nameStartsWith: String?):
            ResourceState<List<CharacterDomain>> {

        return try {
            val response = api.list(nameStartsWith)
            validaResponse(response)
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    ResourceState.Error(context.getString(R.string.erro_conexao))
                }
                else -> {
                    ResourceState.Error(context.getString(R.string.erro_conversao))
                }
            }
        }
    }

    private fun validaResponse(response: Response<CharacterDataResponse>): ResourceState<List<CharacterDomain>> {
        if (response.isSuccessful) {
            response.body()?.let { values ->
                val listDomain = mapToDomain(values)
                return ResourceState.Success(listDomain)
            }
        }
        return ResourceState.Error(response.message())
    }

    private fun mapToDomain(values: CharacterDataResponse): List<CharacterDomain> {
        val results = values.data.results
        return mapper.mapFromCachedNonNull(results)
    }
}

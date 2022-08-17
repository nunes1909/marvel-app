package com.example.marvelapp.data.model.comic.repository

import android.content.Context
import com.example.marvelapp.R
import com.example.marvelapp.data.remote.service.ServiceApi
import com.example.marvelapp.data.model.comic.model.ComicDataResponse
import com.example.marvelapp.data.model.comic.mapper.ComicDataMapper
import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.domain.model.comic.repository.ComicRepository
import com.example.marvelapp.util.state.ResourceState
import retrofit2.Response
import java.io.IOException

class ComicRepositoryImpl(
    private val api: ServiceApi,
    private val mapper: ComicDataMapper,
    private val context: Context
) : ComicRepository {
    override suspend fun getComic(characterId: Int): ResourceState<List<ComicDomain>> {
        return try {
            val response = api.getComics(characterId)
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

    private fun validaResponse(response: Response<ComicDataResponse>):
            ResourceState<List<ComicDomain>> {

        if (response.isSuccessful) {
            response.body()?.let { value ->
                val listDomain = mapToDomain(value)
                return ResourceState.Success(listDomain)
            }
        }
        return ResourceState.Error(response.message())
    }

    private fun mapToDomain(value: ComicDataResponse): List<ComicDomain> {
        val results = value.data.results
        return mapper.mapFromDomainNonNull(results)
    }
}

package com.example.marvelapp.data.di

import com.example.marvelapp.data.character.remote.mapper.CharacterDataMapper
import com.example.marvelapp.data.character.remote.repository.CharacterRepositoryImpl
import com.example.marvelapp.data.remote.retrofit.MarvelRetrofit
import com.example.marvelapp.data.remote.service.ServiceApi
import com.example.marvelapp.domain.character.repository.CharacterRepository
import com.example.marvelapp.util.base.GenericMapper
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<OkHttpClient> { MarvelRetrofit().getOkHttpClient() }
    single<String> { MarvelRetrofit().generatorMd5Hash(get()) }
    single<Retrofit> { MarvelRetrofit().getRetrofit(get()) }
    single<ServiceApi> { MarvelRetrofit().getApiService(get()) }
}

val modules = module {
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get()) }
    factory { CharacterDataMapper() }
}

val dataModules = listOf<Module>(
    apiModule, modules
)
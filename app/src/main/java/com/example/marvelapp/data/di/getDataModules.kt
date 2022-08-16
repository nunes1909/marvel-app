package com.example.marvelapp.data.di

import com.example.marvelapp.data.remote.retrofit.MarvelRetrofit
import com.example.marvelapp.data.remote.service.ServiceApi
import com.example.marvelapp.data.local.database.MarvelDataBase
import com.example.marvelapp.data.model.character.mapper.CharacterDataMapper
import com.example.marvelapp.data.model.character.repository.CharacterRepositoryImpl
import com.example.marvelapp.data.model.comic.mapper.ComicDataMapper
import com.example.marvelapp.data.model.comic.repository.ComicRepositoryImpl
import com.example.marvelapp.domain.character.repository.CharacterRepository
import com.example.marvelapp.domain.comic.repository.ComicRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<OkHttpClient> { MarvelRetrofit().getOkHttpClient() }

    single<String> { MarvelRetrofit().generatorMd5Hash(get()) }

    single<Retrofit> { MarvelRetrofit().getRetrofit(get()) }

    single<ServiceApi> { MarvelRetrofit().getApiService(get()) }
}

val dbModule = module {
    single { get<MarvelDataBase>().getMarvelDao() }

    single { MarvelDataBase.getInstance(androidContext()) }
}

val modules = module {
    factory { CharacterDataMapper() }

    factory { ComicDataMapper() }

    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get(), get()) }

    factory<ComicRepository> { ComicRepositoryImpl(get(), get(), get()) }
}

val dataModules = listOf<Module>( apiModule, modules, dbModule )

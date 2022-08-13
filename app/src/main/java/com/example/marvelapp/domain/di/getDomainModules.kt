package com.example.marvelapp.domain.di

import com.example.marvelapp.domain.character.repository.CharacterRepository
import com.example.marvelapp.domain.character.usecase.GetCharacterUseCase
import com.example.marvelapp.domain.character.usecase.GetCharacterUseCaseImpl
import com.example.marvelapp.domain.character.usecase.favoritos.GetFavoritosUseCase
import com.example.marvelapp.domain.character.usecase.favoritos.GetFavoritosUseCaseImpl
import com.example.marvelapp.domain.character.usecase.favoritos.SaveFavoritosUseCase
import com.example.marvelapp.domain.character.usecase.favoritos.SaveFavoritosUseCaseImpl
import com.example.marvelapp.domain.comic.repository.ComicRepository
import com.example.marvelapp.domain.comic.usecase.GetComicUseCase
import com.example.marvelapp.domain.comic.usecase.GetComicUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetCharacterUseCase> { GetCharacterUseCaseImpl(get()) }
    factory<CharacterRepository> { get() }

    factory<GetComicUseCase> { GetComicUseCaseImpl(get()) }
    factory<ComicRepository> { get() }

    factory<SaveFavoritosUseCase> { SaveFavoritosUseCaseImpl(get()) }
    factory<GetFavoritosUseCase> { GetFavoritosUseCaseImpl(get()) }
}

val domainModules = listOf<Module>(
    useCasesModule
)
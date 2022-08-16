package com.example.marvelapp.domain.di

import com.example.marvelapp.domain.model.character.repository.CharacterRepository
import com.example.marvelapp.domain.model.character.usecase.GetCharacterUseCase
import com.example.marvelapp.domain.model.character.usecase.GetCharacterUseCaseImpl
import com.example.marvelapp.domain.model.character.usecase.favoritos.*
import com.example.marvelapp.domain.model.comic.repository.ComicRepository
import com.example.marvelapp.domain.model.comic.usecase.GetComicUseCase
import com.example.marvelapp.domain.model.comic.usecase.GetComicUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetCharacterUseCase> { GetCharacterUseCaseImpl(get()) }

    factory<CharacterRepository> { get() }

    factory<GetComicUseCase> { GetComicUseCaseImpl(get()) }

    factory<ComicRepository> { get() }

    factory<SaveFavoritosUseCase> { SaveFavoritosUseCaseImpl(get()) }

    factory<GetFavoritosUseCase> { GetFavoritosUseCaseImpl(get()) }

    factory<DeleteCharacterUseCase> { DeleteCharacterUseCaseImpl(get()) }
}

val domainModules = listOf<Module>( useCasesModule )

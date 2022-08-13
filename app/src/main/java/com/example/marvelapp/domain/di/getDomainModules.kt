package com.example.marvelapp.domain.di

import com.example.marvelapp.domain.character.repository.CharacterRepository
import com.example.marvelapp.domain.character.usecase.ListCharacterUseCase
import com.example.marvelapp.domain.character.usecase.ListCharacterUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule = module {
    factory<ListCharacterUseCase> { ListCharacterUseCaseImpl(get()) }
    factory<CharacterRepository> { get() }

}

val domainModules = listOf<Module>(
    useCasesModule
)
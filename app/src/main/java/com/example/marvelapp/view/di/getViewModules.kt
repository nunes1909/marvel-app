package com.example.marvelapp.view.di

import com.example.marvelapp.view.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.ui.lista.ListaCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModules = module {
    viewModel {
        ListaCharacterViewModel(
            get(), get()
        )
    }

    factory { CharacterViewMapper() }
}

val viewModules = listOf<Module>(
    uiModules
)
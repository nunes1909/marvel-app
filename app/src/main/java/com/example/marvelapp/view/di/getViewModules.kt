package com.example.marvelapp.view.di

import com.example.marvelapp.view.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.comic.mapper.ComicViewMapper
import com.example.marvelapp.view.ui.detalhes.DetalhesCharacterViewModel
import com.example.marvelapp.view.ui.lista.ListaCharacterViewModel
import com.example.marvelapp.view.ui.pesquisa.PesquisaCharacterFragment
import com.example.marvelapp.view.ui.pesquisa.PesquisaCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.get
import org.koin.dsl.module

val uiModules = module {
    viewModel {
        ListaCharacterViewModel(
            get(), get()
        )
    }

    viewModel {
        PesquisaCharacterViewModel(
            get(), get()
        )
    }

    viewModel {
        DetalhesCharacterViewModel(
            get(), get()
        )
    }

    factory { CharacterViewMapper() }
    factory { ComicViewMapper() }
}

val viewModules = listOf<Module>(
    uiModules
)
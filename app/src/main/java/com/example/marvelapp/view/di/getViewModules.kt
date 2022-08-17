package com.example.marvelapp.view.di

import com.example.marvelapp.view.model.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.model.comic.mapper.ComicViewMapper
import com.example.marvelapp.view.ui.features.detalhes.DetalhesCharacterViewModel
import com.example.marvelapp.view.ui.features.favoritos.FavoritosCharacterViewModel
import com.example.marvelapp.view.ui.features.lista.ListaCharacterViewModel
import com.example.marvelapp.view.ui.features.pesquisa.PesquisaCharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModules = module {
    viewModel {ListaCharacterViewModel(get(), get())}

    viewModel {PesquisaCharacterViewModel(get(), get())}

    viewModel {DetalhesCharacterViewModel(get(), get(), get(), get())}

    viewModel {FavoritosCharacterViewModel(get(), get(), get(), get())}

    factory { CharacterViewMapper() }

    factory { ComicViewMapper() }
}

val viewModules = listOf<Module>(uiModules)

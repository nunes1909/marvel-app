package com.example.marvelapp.view.ui.features.detalhes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.model.character.usecase.favoritos.SaveFavoritosUseCase
import com.example.marvelapp.domain.model.comic.model.ComicDomain
import com.example.marvelapp.domain.model.comic.usecase.GetComicUseCase
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.model.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.model.character.model.CharacterView
import com.example.marvelapp.view.model.comic.mapper.ComicViewMapper
import com.example.marvelapp.view.model.comic.model.ComicView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetalhesCharacterViewModel(
    private val getUseCase: GetComicUseCase,
    private val saveFavoritosUseCase: SaveFavoritosUseCase,
    private val mapperComic: ComicViewMapper,
    private val mapperCharacter: CharacterViewMapper
) : ViewModel() {

    private val _detalhes = MutableStateFlow<ResourceState<List<ComicView>>>(ResourceState.Load())
    val detalhes: StateFlow<ResourceState<List<ComicView>>> = _detalhes

    init {
        _detalhes.value = ResourceState.Load()
    }

    fun getDetalhes(characterId: Int) = viewModelScope.launch {
        val resource = getUseCase(characterId)
        _detalhes.value = validaResource(resource)
    }

    private fun validaResource(resource: ResourceState<List<ComicDomain>>):
            ResourceState<List<ComicView>> {

        resource.data?.let { value ->
            val comicView = mapToView(value)
            return ResourceState.Success(comicView)
        }
        return ResourceState.Error(resource.message)
    }

    private fun mapToView(value: List<ComicDomain>): List<ComicView> {
        return mapperComic.mapToCachedNonNull(value)
    }

    fun salvar(character: CharacterView) = viewModelScope.launch {
        val characterDomain = mapperCharacter.mapFromCached(character)
        saveFavoritosUseCase(characterDomain)
    }
}

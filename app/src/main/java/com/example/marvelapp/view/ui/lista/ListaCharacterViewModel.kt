package com.example.marvelapp.view.ui.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.usecase.ListCharacterUseCase
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.character.model.CharacterView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaCharacterViewModel(
    private val useCase: ListCharacterUseCase,
    private val mapper: CharacterViewMapper
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<CharacterView>>>(ResourceState.Load())
    val list: StateFlow<ResourceState<List<CharacterView>>> = _list

    init {
        getCharacter()
    }

    private fun getCharacter() = viewModelScope.launch {
        val listDomain = useCase()
        _list.value = validaResource(listDomain)
    }

    /**
     * Este método valida se o data é diferente de null e retorna
     * um resource success do mapper
     *
     * Caso seja null, retorna resource error
     */
    private fun validaResource(
        resourceDomain: ResourceState<List<CharacterDomain>>
    ): ResourceState<List<CharacterView>> {
        resourceDomain.data?.let { values ->
            return ResourceState.Success(
                mapperView(values)
            )
        }
        return ResourceState.Error(resourceDomain.message)
    }

    private fun mapperView(
        values: List<CharacterDomain>
    ): List<CharacterView> {
        return mapper.mapToCachedNonNull(values)
    }
}

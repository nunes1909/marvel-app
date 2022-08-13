package com.example.marvelapp.view.ui.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.usecase.GetCharacterUseCase
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.character.model.CharacterView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaCharacterViewModel(
    private val useCase: GetCharacterUseCase,
    private val mapper: CharacterViewMapper
) : ViewModel() {

    private val _list = MutableStateFlow<ResourceState<List<CharacterView>>>(ResourceState.Load())
    val list: StateFlow<ResourceState<List<CharacterView>>> = _list

    init {
        getCharacter()
    }

    private fun getCharacter() = viewModelScope.launch {
        val resource = useCase()
        _list.value = validaResource(resource)
    }

    /**
     * Este método valida se os dados são diferente de null e retorna
     * um resource success do mapper
     *
     * Caso seja null, retorna resource error
     */
    private fun validaResource(
        resource: ResourceState<List<CharacterDomain>>
    ): ResourceState<List<CharacterView>> {
        resource.data?.let { values ->
            return ResourceState.Success(
                mapToView(values)
            )
        }
        return ResourceState.Error(resource.message)
    }

    private fun mapToView(values: List<CharacterDomain>) =
        mapper.mapToCachedNonNull(values)
}

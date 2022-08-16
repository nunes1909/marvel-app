package com.example.marvelapp.view.ui.features.pesquisa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.model.character.model.CharacterDomain
import com.example.marvelapp.domain.model.character.usecase.GetCharacterUseCase
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.character.model.CharacterView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PesquisaCharacterViewModel(
    private val useCase: GetCharacterUseCase,
    private val mapper: CharacterViewMapper
) : ViewModel() {

    private val _pesquisa =
        MutableStateFlow<ResourceState<List<CharacterView>>>(ResourceState.Empty())
    val pesquisa: StateFlow<ResourceState<List<CharacterView>>> = _pesquisa

    init {
        _pesquisa.value = ResourceState.Load()
    }

    fun getCharacter(nameStartsWith: String) = viewModelScope.launch {
        val resource = useCase(nameStartsWith)
        _pesquisa.value = validaResource(resource)
    }

    private fun validaResource(
        resource: ResourceState<List<CharacterDomain>>
    ): ResourceState<List<CharacterView>> {
        resource.data?.let { values ->
            val characterView = mapToView(values)
            return ResourceState.Success(characterView)
        }
        return ResourceState.Error(resource.message)
    }

    private fun mapToView(values: List<CharacterDomain>): List<CharacterView> {
        return mapper.mapToCachedNonNull(values)
    }
}

package com.example.marvelapp.view.ui.lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.character.usecase.ListCharacterUseCase
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.character.model.CharacterView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

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
        safeGet()
    }

    private suspend fun safeGet() {
        try {
            val listCharacters = useCase()
            listCharacters?.let {
                val list = mapper.mapToCachedNonNull(it)
                _list.value = handleList(list)
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _list.value = ResourceState.Error("Erro de conexão.")
                else -> _list.value = ResourceState.Error("Falha na conversão de dados.")
            }
        }
    }

    private fun handleList(list: List<CharacterView>?): ResourceState<List<CharacterView>> {
        if (list != null) {
            return ResourceState.Success(list)
        }
        return ResourceState.Error("Um erro ocorreu")
    }
}

package com.example.marvelapp.view.ui.favoritos

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.R
import com.example.marvelapp.domain.character.model.CharacterDomain
import com.example.marvelapp.domain.character.usecase.favoritos.DeleteCharacterUseCase
import com.example.marvelapp.domain.character.usecase.favoritos.GetFavoritosUseCase
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.character.mapper.CharacterViewMapper
import com.example.marvelapp.view.character.model.CharacterView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoritosCharacterViewModel(
    private val getFavUseCase: GetFavoritosUseCase,
    private val deleteFavUseCase: DeleteCharacterUseCase,
    private val mapper: CharacterViewMapper,
    private val context: Context
) : ViewModel() {

    private val _getFavoritos =
        MutableStateFlow<ResourceState<List<CharacterView>>>(ResourceState.Empty())
    val getFavoritos: StateFlow<ResourceState<List<CharacterView>>> = _getFavoritos

    init {
        getFavoritos()
    }

    private fun getFavoritos() = viewModelScope.launch {
        getFavUseCase().collect { values ->
            _getFavoritos.value = validaResource(values)
        }
    }

    private fun validaResource(values: List<CharacterDomain>):
            ResourceState<List<CharacterView>> {

        return try {
            ResourceState.Success(mapToView(values))
        } catch (t: Throwable) {
            Timber.tag("FavoritosCharacterViewModel").e("Error -> $t")
            ResourceState.Error(context.getString(R.string.erro_favoritos))
        }
    }

    private fun mapToView(values: List<CharacterDomain>) =
        mapper.mapToCachedNonNull(values)

    fun delete(character: CharacterView) = viewModelScope.launch {
        val characterDomain = mapToDomain(character)
        deleteFavUseCase(characterDomain)
    }

    private fun mapToDomain(character: CharacterView): CharacterDomain =
        mapper.mapFromCached(character)
}

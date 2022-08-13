package com.example.marvelapp.view.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.databinding.FragmentFavoritosCharacterBinding
import com.example.marvelapp.util.base.BaseFragment
import com.example.marvelapp.util.extensions.hide
import com.example.marvelapp.util.extensions.show
import com.example.marvelapp.util.extensions.toast
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.ui.adapters.CharacterAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritosCharacterFragment :
    BaseFragment<FragmentFavoritosCharacterBinding, FavoritosCharacterViewModel>() {

    override val viewModel: FavoritosCharacterViewModel by viewModel()
    private val favoritosAdapter by lazy { CharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraClickAdapter()
        observer()
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.getFavoritos.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { values ->
                        favoritosAdapter.characters = values.toList()
                    }
                }
                is ResourceState.Error -> {
                    resource.message?.let { message ->
                        toast(message)
                        Timber.tag("ListaCharacterFragment").e("Erro -> $message")
                    }
                }
                else -> {}
            }
        }
    }

    private fun configuraClickAdapter() = with(binding) {
        favoritosAdapter.setOnItemClickListener { character ->
            val action = FavoritosCharacterFragmentDirections
                .actionFavoritosCharacterFragmentToDetalhesCharacterFragment(character)

            findNavController().navigate(action)
        }
    }

    private fun configuraRecyclerView() = with(binding) {
        rvFavoritosCharacter.apply {
            adapter = favoritosAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritosCharacterBinding =
        FragmentFavoritosCharacterBinding.inflate(inflater, container, false)
}
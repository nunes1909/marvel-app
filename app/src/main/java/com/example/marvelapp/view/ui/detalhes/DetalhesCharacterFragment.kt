package com.example.marvelapp.view.ui.detalhes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentDetalhesCharacterBinding
import com.example.marvelapp.util.base.BaseFragment
import com.example.marvelapp.util.extensions.hide
import com.example.marvelapp.util.extensions.limitDescription
import com.example.marvelapp.util.extensions.show
import com.example.marvelapp.util.extensions.toast
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.character.model.CharacterView
import com.example.marvelapp.view.ui.adapters.ComicAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetalhesCharacterFragment :
    BaseFragment<FragmentDetalhesCharacterBinding, DetalhesCharacterViewModel>() {

    private val args: DetalhesCharacterFragmentArgs by navArgs()
    private lateinit var characterView: CharacterView
    override val viewModel: DetalhesCharacterViewModel by viewModel()
    private val comicAdapter by lazy {
        ComicAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterView = args.character
        viewModel.getDetalhes(characterView.id)

        configuraRecyclerView()
        carregaCharacterDetalhes()
        observe()
    }

    private fun observe() = lifecycleScope.launch {
        viewModel.detalhes.collect{ resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { vales ->
                        binding.progressDetalhesCharacter.hide()
                        comicAdapter.comics = vales.toList()
                    }
                }
                is ResourceState.Error -> {
                    resource.message?.let { message ->
                        toast(message)
                        Timber.tag("DetalhesCharacterFragment").e("Error -> $message")
                    }
                }
                is ResourceState.Load -> {
                    binding.progressDetalhesCharacter.show()
                }
                else -> {}
            }
        }
    }

    private fun carregaCharacterDetalhes() = with(binding) {
        nomeDetalhesCharacter.text = characterView.name

        if (characterView.description.isEmpty()) {
            descricaoDetalhesCharacter.text = requireContext().getString(R.string.descricao_padrao)
        } else {
            descricaoDetalhesCharacter.text = characterView.description.limitDescription(80)
        }

        imageDetalhesCharacter.load(
            characterView.thumbnail.path + "." + characterView.thumbnail.extension
        )
    }

    private fun configuraRecyclerView() = with(binding) {
        rvDetalhesCharacter.apply {
            adapter = comicAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesCharacterBinding =
        FragmentDetalhesCharacterBinding.inflate(inflater, container, false)
}
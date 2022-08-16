package com.example.marvelapp.view.ui.features.detalhes

import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentDetalhesCharacterBinding
import com.example.marvelapp.util.base.BaseFragment
import com.example.marvelapp.util.extensions.*
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.character.model.CharacterView
import com.example.marvelapp.view.ui.adapters.ComicAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetalhesCharacterFragment :
    BaseFragment<FragmentDetalhesCharacterBinding, DetalhesCharacterViewModel>() {

    private lateinit var characterView: CharacterView
    private val args: DetalhesCharacterFragmentArgs by navArgs()
    override val viewModel: DetalhesCharacterViewModel by viewModel()
    private val comicAdapter by lazy { ComicAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterView = args.character
        viewModel.getDetalhes(characterView.id)

        configuraRecyclerView()
        carregaCharacterDetalhes()
        observe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fav, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_favoritar -> {
                viewModel.salvar(characterView)
                toast(getString(R.string.mensagem_favoritar))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesCharacterBinding =
        FragmentDetalhesCharacterBinding.inflate(inflater, container, false)
}

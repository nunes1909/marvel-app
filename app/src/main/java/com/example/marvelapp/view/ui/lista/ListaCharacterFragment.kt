package com.example.marvelapp.view.ui.lista

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.databinding.FragmentListaCharacterBinding
import com.example.marvelapp.util.base.BaseFragment
import com.example.marvelapp.util.extensions.hide
import com.example.marvelapp.util.extensions.show
import com.example.marvelapp.util.extensions.toast
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.adapters.CharacterAdapter
import com.example.marvelapp.view.ui.detalhes.DetalhesCharacterFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaCharacterFragment :
    BaseFragment<FragmentListaCharacterBinding, ListaCharacterViewModel>() {

    override val viewModel: ListaCharacterViewModel by viewModel()
    private val characterAdapter by lazy {
        CharacterAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        clickAdapter()
        observer()
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.list.collect { resources ->
            when (resources) {
                is ResourceState.Success -> {
                    resources.data?.let { values ->
                        binding.progressListaCharacter.hide()
                        characterAdapter.characters = values.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressListaCharacter.hide()
                    resources.message?.let {
                        toast(it)
                    }
                }
                is ResourceState.Load -> {
                    binding.progressListaCharacter.show()
                }
                else -> {}
            }
        }
    }

    private fun clickAdapter() = with(binding) {
        characterAdapter.setOnItemClickListener { character ->
            val action = ListaCharacterFragmentDirections
                .actionListaCharacterFragmentToDetalhesCharacterFragment(character)

            findNavController().navigate(action)
        }
    }

    private fun configuraRecyclerView() = with(binding) {
        rvListaCharacter.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListaCharacterBinding =
        FragmentListaCharacterBinding.inflate(inflater, container, false)

}
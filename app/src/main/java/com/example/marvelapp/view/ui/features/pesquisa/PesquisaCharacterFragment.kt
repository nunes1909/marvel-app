package com.example.marvelapp.view.ui.features.pesquisa

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.databinding.FragmentPesquisaCharacterBinding
import com.example.marvelapp.util.base.BaseFragment
import com.example.marvelapp.util.constants.Constants.DEFAULT_QUERY
import com.example.marvelapp.util.constants.Constants.PESQUISA_QUERY
import com.example.marvelapp.util.extensions.hide
import com.example.marvelapp.util.extensions.show
import com.example.marvelapp.util.extensions.toast
import com.example.marvelapp.util.state.ResourceState
import com.example.marvelapp.view.ui.adapters.CharacterAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PesquisaCharacterFragment :
    BaseFragment<FragmentPesquisaCharacterBinding, PesquisaCharacterViewModel>() {

    override val viewModel: PesquisaCharacterViewModel by viewModel()
    private val characterAdapter by lazy { CharacterAdapter() }
    private var valor: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
        configuraClickAdapter()
        configuraPesquisa(valor)
        observer()
    }

    private fun configuraPesquisa(query: String) = with(binding) {
        tituloPesquisaCharacter.setText(query)

        tituloPesquisaCharacter.setOnEditorActionListener { _, actionId, _ ->
            val clicouGo = actionId == EditorInfo.IME_ACTION_GO

            if (clicouGo) {
                pesquisaCharacter()
                valor = tituloPesquisaCharacter.text?.trim().toString()
                true
            } else {
                false
            }
        }

        tituloPesquisaCharacter.setOnKeyListener { _, code, event ->
            val clicouEnter = event.action == KeyEvent.ACTION_DOWN &&
                    code == KeyEvent.KEYCODE_ENTER

            if (clicouEnter) {
                pesquisaCharacter()
                valor = tituloPesquisaCharacter.text?.trim().toString()
                true
            } else {
                false
            }
        }
    }

    private fun pesquisaCharacter() = with(binding) {
        tituloPesquisaCharacter.text.toString().trim().let {
            viewModel.getCharacter(it)
        }
    }

    private fun observer() = lifecycleScope.launch {
        viewModel.pesquisa.collect { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    resource.data?.let { values ->
                        binding.progressPesquisaCharacter.hide()
                        characterAdapter.characters = values.toList()
                    }
                }
                is ResourceState.Error -> {
                    binding.progressPesquisaCharacter.hide()
                    resource.message?.let { message ->
                        toast(message)
                        Timber.tag("PesquisaCharacterFragment").e("Erro -> $message")
                    }
                }
                is ResourceState.Load -> {
                    binding.progressPesquisaCharacter.show()
                }
                else -> {}
            }
        }
    }

    private fun configuraClickAdapter() = with(binding) {
        characterAdapter.setOnItemClickListener { character ->
            val action = PesquisaCharacterFragmentDirections
                .actionPesquisaCharacterFragmentToDetalhesCharacterFragment(character)

            findNavController().navigate(action)
        }
    }

    private fun configuraRecyclerView() = with(binding) {
        rvPesquisaCharacter.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPesquisaCharacterBinding =
        FragmentPesquisaCharacterBinding.inflate(inflater, container, false)
}

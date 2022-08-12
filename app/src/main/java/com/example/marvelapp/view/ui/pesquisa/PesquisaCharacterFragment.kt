package com.example.marvelapp.view.ui.pesquisa

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvelapp.databinding.FragmentPesquisaCharacterBinding
import com.example.marvelapp.view.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PesquisaCharacterFragment :
    BaseFragment<FragmentPesquisaCharacterBinding, PesquisaCharacterViewModel>() {

    override val viewModel: PesquisaCharacterViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPesquisaCharacterBinding =
        FragmentPesquisaCharacterBinding.inflate(inflater, container, false)
}
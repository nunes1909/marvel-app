package com.example.marvelapp.view.ui.detalhes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvelapp.databinding.FragmentDetalhesCharacterBinding
import com.example.marvelapp.view.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesCharacterFragment :
    BaseFragment<FragmentDetalhesCharacterBinding, DetalhesCharacterViewModel>() {

    override val viewModel: DetalhesCharacterViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesCharacterBinding =
        FragmentDetalhesCharacterBinding.inflate(inflater, container, false)
}
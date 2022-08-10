package com.example.marvelapp.view.ui.lista

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvelapp.databinding.FragmentListaCharacterBinding
import com.example.marvelapp.view.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListaCharacterFragment :
    BaseFragment<FragmentListaCharacterBinding, ListaCharacterViewModel>() {

    override val viewModel: ListaCharacterViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListaCharacterBinding =
        FragmentListaCharacterBinding.inflate(inflater, container, false)

}
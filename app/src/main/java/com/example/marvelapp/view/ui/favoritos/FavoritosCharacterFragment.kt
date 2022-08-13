package com.example.marvelapp.view.ui.favoritos

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvelapp.databinding.FragmentFavoritosCharacterBinding
import com.example.marvelapp.util.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritosCharacterFragment :
    BaseFragment<FragmentFavoritosCharacterBinding, FavoritosCharacterViewModel>() {

    override val viewModel: FavoritosCharacterViewModel by viewModel()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritosCharacterBinding =
        FragmentFavoritosCharacterBinding.inflate(inflater, container, false)
}
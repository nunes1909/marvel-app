package com.example.marvelapp.view.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment <viewBinding: ViewBinding, viewModel: ViewModel> : Fragment() {

    private var _binding: viewBinding? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): viewBinding?

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
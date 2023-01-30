package com.example.cleanarchitectureexample.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    private val viewBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var binding: VB? = null
    val vb get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = viewBinding(inflater, container, false)

        initViews()
        initViewModel()

        return vb.root
    }

    abstract fun initViewModel()

    abstract fun initViews()

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
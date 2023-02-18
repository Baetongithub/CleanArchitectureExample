package com.example.cleanarchitectureexample.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.cleanarchitectureexample.presentation.ui.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseFragment<VB : ViewBinding>(
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

    protected open fun <T> StateFlow<UIState<T>>.collectState(
        onLoading: () -> Unit,
        onError: (message: String) -> Unit,
        onSuccess: (data: T) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@collectState.collect {
                    when (it) {
                        is UIState.Empty -> {}
                        is UIState.Error -> {
                            onError(it.message)
                        }
                        is UIState.Loading -> {
                            onLoading()
                        }
                        is UIState.Success -> {
                            onSuccess(it.data)
                        }
                    }
                }
            }
        }
    }

    protected open fun initViewModel() {}

    protected open fun initViews() {}

    fun navigateUp() {
        findNavController().navigateUp()
    }

    fun navigate(resId: Int, bundle: Bundle? = null) {
        if (bundle == null)
            findNavController().navigate(resId)
        else
            findNavController().navigate(resId, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
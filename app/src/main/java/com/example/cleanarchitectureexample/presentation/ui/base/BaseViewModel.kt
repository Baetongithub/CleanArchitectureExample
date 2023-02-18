package com.example.cleanarchitectureexample.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureexample.domain.utils.Resource
import com.example.cleanarchitectureexample.presentation.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<Resource<T>>.collectFlow(_state: MutableStateFlow<UIState<T>>) {
        viewModelScope.launch(Dispatchers.IO) {
            this@collectFlow.collect {
                when (it) {
                    is Resource.Error -> {
                        _state.value = UIState.Error(it.message!!)
                    }
                    is Resource.Loading -> _state.value = UIState.Loading()
                    is Resource.Success -> {
                        if (it.data != null)
                            _state.value = UIState.Success(it.data!!)
                    }
                }
            }
        }
    }
}
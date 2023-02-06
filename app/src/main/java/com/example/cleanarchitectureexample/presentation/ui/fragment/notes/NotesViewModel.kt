package com.example.cleanarchitectureexample.presentation.ui.fragment.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.usecases.DeleteNoteUseCase
import com.example.cleanarchitectureexample.domain.usecases.GetNotesUseCase
import com.example.cleanarchitectureexample.domain.utils.Resource
import com.example.cleanarchitectureexample.presentation.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    private val _getNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getNotesState = _getNotesState.asStateFlow()

    fun getNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getNotesUseCase.getNotes().collect {
                when (it) {
                    is Resource.Error -> {
                        _getNotesState.value = UIState.Error(it.message!!)
                    }
                    is Resource.Loading -> _getNotesState.value = UIState.Loading()
                    is Resource.Success -> {
                        if (it.data != null)
                            _getNotesState.value = UIState.Success(it.data)
                    }
                }
            }
        }
    }
}
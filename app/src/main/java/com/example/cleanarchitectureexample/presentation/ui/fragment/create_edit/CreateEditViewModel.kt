package com.example.cleanarchitectureexample.presentation.ui.fragment.create_edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.usecases.CreateNoteUseCase
import com.example.cleanarchitectureexample.domain.utils.Resource
import com.example.cleanarchitectureexample.presentation.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEditViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    private val _createNotesState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNotesState = _createNotesState.asStateFlow()

    fun createNotes(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            createNoteUseCase.createNote(note).collect {
                when (it) {
                    is Resource.Error -> {
                        _createNotesState.value = UIState.Error(it.message!!)
                    }
                    is Resource.Loading -> {
                        _createNotesState.value = UIState.Loading()
                    }
                    is Resource.Success -> {
                        if (it.data != null)
                            _createNotesState.value = UIState.Success(it.data)
                    }
                }
            }
        }
    }
}
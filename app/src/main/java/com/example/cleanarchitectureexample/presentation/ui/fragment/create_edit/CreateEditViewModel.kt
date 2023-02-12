package com.example.cleanarchitectureexample.presentation.ui.fragment.create_edit

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.usecases.CreateNoteUseCase
import com.example.cleanarchitectureexample.domain.usecases.EditNoteUseCase
import com.example.cleanarchitectureexample.presentation.ui.UIState
import com.example.cleanarchitectureexample.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateEditViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    private val _createNotesState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val createNotesState = _createNotesState.asStateFlow()

    private val _editNotesState = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val editNotesState = _editNotesState.asStateFlow()

    fun createNotes(note: Note) {
        createNoteUseCase.createNote(note).collectFlow(_createNotesState)
    }

    fun editNote(note: Note) {
        editNoteUseCase.editNote(note).collectFlow(_editNotesState)
    }
}
package com.example.cleanarchitectureexample.presentation.ui.fragment.notes

import androidx.lifecycle.MutableLiveData
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.usecases.DeleteNoteUseCase
import com.example.cleanarchitectureexample.domain.usecases.GetNotesUseCase
import com.example.cleanarchitectureexample.presentation.ui.UIState
import com.example.cleanarchitectureexample.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    val loading = MutableLiveData<Boolean>()

    private val _getNotesState = MutableStateFlow<UIState<List<Note>>>(UIState.Empty())
    val getNotesState = _getNotesState.asStateFlow()

    private val _deleteNoteUseCase = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteNotesState = _deleteNoteUseCase.asStateFlow()

    fun getNotes() {
        getNotesUseCase.getNotes().collectFlow(_getNotesState)
    }

    fun delete(note: Note) {
        deleteNoteUseCase.deleteNote(note).collectFlow(_deleteNoteUseCase)
    }
}
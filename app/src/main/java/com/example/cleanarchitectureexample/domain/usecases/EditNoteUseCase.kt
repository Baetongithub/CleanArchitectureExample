package com.example.cleanarchitectureexample.domain.usecases

import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.repository.NoteRepository
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun editNote(note: Note) = noteRepository.editNotes(note)
}
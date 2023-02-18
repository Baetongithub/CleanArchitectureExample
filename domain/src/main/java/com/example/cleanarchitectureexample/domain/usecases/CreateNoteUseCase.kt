package com.example.cleanarchitectureexample.domain.usecases

import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    fun createNote(note: Note) = noteRepository.createNotes(note)
}
package com.example.cleanarchitectureexample.domain.usecases

import com.example.cleanarchitectureexample.domain.repository.NoteRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {

    fun getNotes() = noteRepository.getNotes()
}
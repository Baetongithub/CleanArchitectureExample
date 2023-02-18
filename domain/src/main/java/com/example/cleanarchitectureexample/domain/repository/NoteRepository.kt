package com.example.cleanarchitectureexample.domain.repository

import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun createNotes(note: Note): Flow<Resource<Unit>>

    fun editNotes(note: Note): Flow<Resource<Unit>>

    fun deleteNotes(note: Note): Flow<Resource<Unit>>

    fun getNotes(): Flow<Resource<List<Note>>>
}
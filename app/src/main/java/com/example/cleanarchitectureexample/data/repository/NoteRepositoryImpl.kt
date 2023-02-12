package com.example.cleanarchitectureexample.data.repository

import com.example.cleanarchitectureexample.data.base.BaseRepository
import com.example.cleanarchitectureexample.data.local.NoteDao
import com.example.cleanarchitectureexample.data.mappers.toNote
import com.example.cleanarchitectureexample.data.mappers.toNoteEntity
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.repository.NoteRepository
import com.example.cleanarchitectureexample.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : BaseRepository(), NoteRepository {

    override fun createNotes(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.createNote(note.toNoteEntity())
    }

    override fun editNotes(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.editNote(note.toNoteEntity())
    }

    override fun deleteNotes(note: Note): Flow<Resource<Unit>> = doRequest {
        noteDao.deleteNote(note.toNoteEntity())
    }

    override fun getNotes(): Flow<Resource<List<Note>>> = doRequest {
        noteDao.getNotes().map { it.toNote() }
    }
}
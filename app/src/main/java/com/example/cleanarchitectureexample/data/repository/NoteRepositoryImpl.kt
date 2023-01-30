package com.example.cleanarchitectureexample.data.repository

import com.example.cleanarchitectureexample.data.local.NoteDao
import com.example.cleanarchitectureexample.data.mappers.toNote
import com.example.cleanarchitectureexample.data.mappers.toNoteEntity
import com.example.cleanarchitectureexample.domain.model.Note
import com.example.cleanarchitectureexample.domain.repository.NoteRepository
import com.example.cleanarchitectureexample.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override fun createNotes(note: Note): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        delay(1500)
        try {
            val data = noteDao.createNote(note.toNoteEntity())
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun editNotes(note: Note): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        delay(1500)
        try {
            val data = noteDao.editNote(note.toNoteEntity())
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun deleteNotes(note: Note): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        delay(1500)
        try {
            val data = noteDao.deleteNote(note.toNoteEntity())
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getNotes(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        delay(1500)
        try {
            val data = noteDao.getNotes().map { it.toNote() }
            emit(Resource.Success(data))
        } catch (ioException: IOException) {
            emit(Resource.Error(ioException.localizedMessage ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)
}
package com.example.cleanarchitectureexample.data.local

import androidx.room.*
import com.example.cleanarchitectureexample.data.model.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createNote(noteEntity: NoteEntity)

    @Update
    fun editNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getNotes(): List<NoteEntity>
}
package com.example.cleanarchitectureexample.data.mappers

import com.example.cleanarchitectureexample.data.model.NoteEntity
import com.example.cleanarchitectureexample.domain.model.Note

fun Note.toNoteEntity() =
    NoteEntity(
        id,
        title,
        desc,
        createdAt
    )

fun NoteEntity.toNote() =
    Note(
        id,
        title,
        desc,
        createdAt
    )
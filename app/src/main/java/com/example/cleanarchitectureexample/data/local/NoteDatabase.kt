package com.example.cleanarchitectureexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitectureexample.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
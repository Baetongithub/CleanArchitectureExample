package com.example.cleanarchitectureexample.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cleanarchitectureexample.data.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 2,
    autoMigrations = [AutoMigration(from = 1, to = 2, spec = AutoMigrationSpec1To2::class)],
    exportSchema = true
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
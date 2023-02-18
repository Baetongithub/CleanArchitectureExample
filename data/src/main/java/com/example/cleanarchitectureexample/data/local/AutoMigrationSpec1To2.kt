package com.example.cleanarchitectureexample.data.local

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

@RenameColumn(tableName = "notes", fromColumnName = "createdAt", toColumnName = "createdAt")
class AutoMigrationSpec1To2 : AutoMigrationSpec
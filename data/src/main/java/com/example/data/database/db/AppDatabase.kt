package com.example.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.entities.Note
import com.example.data.database.daos.NotesDao
@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
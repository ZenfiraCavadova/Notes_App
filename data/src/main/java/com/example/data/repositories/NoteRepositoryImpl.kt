package com.example.data.repositories

import androidx.lifecycle.LiveData
import com.example.data.database.db.DatabaseManager
import com.example.domain.entities.Note
import com.example.domain.repositories.NoteRepository

class NoteRepositoryImpl:NoteRepository {
    override fun saveNote(note: Note) {
        return DatabaseManager.database.notesDao().insert(note)
    }

    override fun getNote(): LiveData<List<Note>> {
        return DatabaseManager.database.notesDao().getAll()
    }
}
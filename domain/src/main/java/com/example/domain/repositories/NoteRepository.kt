package com.example.domain.repositories

import com.example.domain.entities.Note

interface NoteRepository {
    fun saveNote(note: Note)
}
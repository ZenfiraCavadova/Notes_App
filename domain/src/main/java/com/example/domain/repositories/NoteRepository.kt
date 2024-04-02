package com.example.domain.repositories

import androidx.lifecycle.LiveData
import com.example.domain.entities.Note

interface NoteRepository {
    fun saveNote(note: Note)
    fun getNote(): LiveData<List<Note>>
}
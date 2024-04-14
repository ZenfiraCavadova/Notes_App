package com.example.domain.repositories

import androidx.lifecycle.LiveData
import com.example.domain.entities.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun saveNote(note: Note)
    fun getNote(): Flow<List<Note>>
}
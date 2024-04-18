package com.example.domain.repositories

import androidx.lifecycle.LiveData
import com.example.domain.entities.Note
import com.example.domain.entities.request_models.NoteRequestModel
import com.example.domain.entities.response_models.GetAllNotesResponseModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun loadNotes():GetAllNotesResponseModel

    suspend fun addNoteRemote(noteRequestModel: NoteRequestModel)
    fun saveNote(note: Note)
    fun getNote(): Flow<List<Note>>
}
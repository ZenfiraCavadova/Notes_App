package com.example.data.repositories

import androidx.lifecycle.LiveData
import com.example.data.api.NetworkManager
import com.example.data.database.db.DatabaseManager
import com.example.domain.entities.Note
import com.example.domain.entities.request_models.NoteRequestModel
import com.example.domain.entities.response_models.GetAllNotesResponseModel
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl:NoteRepository {
    private val notesService=NetworkManager.getNotesServiceInstance()

    override suspend fun loadNotes(): GetAllNotesResponseModel {
        return notesService.getAllNotes()
    }

    override suspend fun addNoteRemote(noteRequestModel: NoteRequestModel) {
        notesService.addNote(noteRequestModel)
    }


    override fun saveNote(note: Note) {
        return DatabaseManager.database.notesDao().insert(note)
    }

    override fun getNote(): Flow<List<Note>> {
        return DatabaseManager.database.notesDao().getAll()
    }
}
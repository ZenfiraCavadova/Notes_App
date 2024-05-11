package com.example.data.repositories

import com.example.data.api.NetworkManager
import com.example.data.api.NotesService
import com.example.data.database.daos.NotesDao
import com.example.data.database.db.DatabaseManager
import com.example.data.di.DatabaseModule
import com.example.domain.entities.Note
import com.example.domain.entities.request_models.NoteRequestModel
import com.example.domain.entities.response_models.NoteResponseModel
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val notesService: NotesService,
    private val notesDao: NotesDao
):NoteRepository {
//    private val notesService=NetworkManager.getNotesServiceInstance()

    override suspend fun loadNotes(): List<NoteResponseModel> {
        return notesService.getAllNotes()
    }

    override suspend fun addNoteRemote(noteRequestModel: NoteRequestModel) {
        notesService.addNote(noteRequestModel)
    }


    override fun saveNote(note: Note) {
        return notesDao.insert(note)
    }

    override fun getNote(): Flow<List<Note>> {
        return notesDao.getAll()
    }
}
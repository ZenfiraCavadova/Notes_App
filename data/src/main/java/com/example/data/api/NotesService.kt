package com.example.data.api

import com.example.domain.entities.request_models.NoteRequestModel
import com.example.domain.entities.response_models.GetAllNotesResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesService {

    @GET("/get_notes")
    suspend fun getAllNotes():GetAllNotesResponseModel

    @POST("/add_note")
    suspend fun addNote(
        @Body noteRequestModel: NoteRequestModel)
}
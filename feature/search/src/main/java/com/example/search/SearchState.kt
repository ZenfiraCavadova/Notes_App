package com.example.search

import com.example.domain.entities.Note
import com.example.domain.entities.response_models.NoteResponseModel

data class SearchState(
    val isLoading: Boolean,
    val notes: List<Note>? = null,
    val note: Note? = null,
    val filteredNotes: List<NoteResponseModel>? = null
)

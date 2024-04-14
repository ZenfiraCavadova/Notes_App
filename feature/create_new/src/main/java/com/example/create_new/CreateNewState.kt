package com.example.create_new

import com.example.domain.entities.Note

data class CreateNewState(
    val isLoading: Boolean,
    val notes: List<Note>? = null,
    val note: Note? = null
)

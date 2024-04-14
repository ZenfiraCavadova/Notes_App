package com.example.home

import com.example.domain.entities.Note

data class HomeState(
    val isLoading: Boolean,
    val notes: List<Note>? = null,
    val note: Note? = null
)

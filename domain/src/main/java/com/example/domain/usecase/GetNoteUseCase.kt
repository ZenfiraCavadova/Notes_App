package com.example.domain.usecase

import com.example.domain.entities.Note
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val notesRepository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return notesRepository.getNote()
    }
}
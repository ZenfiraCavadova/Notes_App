package com.example.domain.usecase

import com.example.domain.entities.Note
import com.example.domain.repositories.NoteRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
        operator fun invoke(note: Note) {
            noteRepository.saveNote(note)
        }

}
package com.example.create_new

sealed class CreateNewEffect {
    object OnNoteAdded : CreateNewEffect()
}

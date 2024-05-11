package com.example.data.di

import com.example.data.repositories.NoteRepositoryImpl
import com.example.domain.repositories.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindNotesRepository(notesRepositoryImpl: NoteRepositoryImpl): NoteRepository
}
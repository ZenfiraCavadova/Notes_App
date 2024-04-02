package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repositories.NoteRepositoryImpl
import com.example.domain.entities.Note
import com.example.domain.repositories.NoteRepository

class HomeViewModel:ViewModel() {
    private val noteRepository: NoteRepository by lazy { NoteRepositoryImpl() }
    private val _livedata=MutableLiveData<List<Note>>()
    val liveData :LiveData<List<Note>> = noteRepository.getNote()

}

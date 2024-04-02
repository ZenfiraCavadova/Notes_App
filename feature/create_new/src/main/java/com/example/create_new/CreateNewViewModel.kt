package com.example.create_new

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repositories.NoteRepositoryImpl
import com.example.domain.entities.Note
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateNewViewModel():ViewModel() {
    private val noteRepository:NoteRepository by lazy { NoteRepositoryImpl() }
    private val _livedata=MutableLiveData<String>()
    val liveData: LiveData<List<Note>> =noteRepository.getNote()
    fun saveNote(title: String, description: String) {
        val note = Note(title, description)
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.saveNote(note)
        }
    }


}
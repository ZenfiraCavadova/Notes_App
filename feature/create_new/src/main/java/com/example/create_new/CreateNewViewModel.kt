package com.example.create_new

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repositories.NoteRepositoryImpl
import com.example.domain.entities.Note
import com.example.domain.repositories.NoteRepository

class CreateNewViewModel():ViewModel() {
    private val _livedata=MutableLiveData<String>()
    val liveData: LiveData<String> = _livedata
    private val noteRepository:NoteRepository by lazy { NoteRepositoryImpl() }
    fun saveNote(title: String, description: String) {
        val note = Note(title, description)
        noteRepository.saveNote(note)
        _livedata.postValue("Note Saved!")
    }

}
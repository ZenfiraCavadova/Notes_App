package com.example.create_new

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.data.repositories.NoteRepositoryImpl
import com.example.domain.entities.Note
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CreateNewViewModel():BaseViewModel<CreateNewState,CreateNewEffect,CreateNewEvent>() {
    private val noteRepository:NoteRepository by lazy { NoteRepositoryImpl() }
//    private val _livedata=MutableLiveData<String>()
//    val liveData: LiveData<List<Note>> =noteRepository.getNote()
//    fun saveNote(title: String, description: String,creationDate:Long) {
//        val note = Note(title, description, creationDate)
//        viewModelScope.launch(Dispatchers.IO) {
//            noteRepository.saveNote(note)
//        }
//    }

    init {
        noteRepository.getNote()
            .onEach { currentDatabaseValue ->
                setState(
                    getCurrentState()
                        .copy(
                            notes = currentDatabaseValue
                        )
                )
            }.launchIn(viewModelScope)
    }
    override fun onEventUpdate(event: CreateNewEvent) {
        when (event) {
            is CreateNewEvent.SaveNote -> saveNote(
                event.title, event.description

            )
        }
    }
    fun saveNote(title: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.saveNote(Note(title, description, System.currentTimeMillis()))
            postEffect(CreateNewEffect.OnNoteAdded)
        }
    }

//        viewModelScope.launch {
//            setState(
//                getCurrentState().copy(
//                    isLoading = true
//                )
//            )
//            delay(4000)
//            setState(
//                getCurrentState().copy(
//                    note = Note(title, description, System.currentTimeMillis()),
//                    isLoading = false
//                )
//            )
//        }
//    }

    override fun getInitialState(): CreateNewState = CreateNewState(isLoading = false)
}
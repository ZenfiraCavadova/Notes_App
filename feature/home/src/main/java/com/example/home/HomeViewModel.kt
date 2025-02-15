package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.data.repositories.NoteRepositoryImpl
import com.example.domain.entities.Note
import com.example.domain.entities.response_models.NoteResponseModel
import com.example.domain.repositories.NoteRepository
import com.example.domain.usecase.GetNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository : NoteRepository,
    getNoteUseCase: GetNoteUseCase
) : BaseViewModel<HomeState,HomeEffect, HomeEvent>()  {
//    private val noteRepository: NoteRepository by lazy { NoteRepositoryImpl() }
    private val _notes = MutableStateFlow<List<NoteResponseModel>>(emptyList())
    val notes: StateFlow<List<NoteResponseModel>> = _notes
//    private val _livedata=MutableLiveData<List<Note>>()
//    val liveData :LiveData<List<Note>> = noteRepository.getNote()

    init {
        getNoteUseCase()
            .onEach { currentDatabaseValue ->
                setState(
                    getCurrentState()
                        .copy(
                            notes = currentDatabaseValue
                        )
                )
            }.launchIn(viewModelScope)
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch(Dispatchers.IO) {
//            _notes.emit(noteRepository.loadNotes())
            val notes = noteRepository.loadNotes()
            notes
        }
    }



//    override fun onEventUpdate(event: HomeEvent) {
//        when (event) {
//            is HomeEvent.SaveNote -> saveNote(
//                event.title, event.description
//
//            )
//        }
//    }
//    viewModelScope.launch(Dispatchers.IO) {
//        noteRepository.saveNote(Note(title, description, System.currentTimeMillis()))
//        postEffect(HomeEffect.OnNoteAdded)
//    }


//    fun saveNote(title: String, description: String) {
//        viewModelScope.launch {
//            setState(getCurrentState().copy(isLoading = true))
//            delay(4000) // Simulating a delay
//            setState(
//                getCurrentState().copy(
//                    note = Note(title, description, System.currentTimeMillis()),
//                    isLoading = false
//                )
//            )
//        }
//    }
    override fun getInitialState(): HomeState = HomeState(isLoading = false)
}
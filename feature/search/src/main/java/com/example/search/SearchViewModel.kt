package com.example.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.BaseViewModel
import com.example.data.repositories.NoteRepositoryImpl
import com.example.domain.entities.response_models.NoteResponseModel
import com.example.domain.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel: BaseViewModel<SearchState, SearchEffect, SearchEvent>() {
    private val noteRepository: NoteRepository by lazy { NoteRepositoryImpl() }
    private val _notes = MutableStateFlow<List<NoteResponseModel>>(emptyList())
    val notes: StateFlow<List<NoteResponseModel>> = _notes
//    private val _livedata= MutableLiveData<String>()
//    val liveData:LiveData<String> = _livedata

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
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch(Dispatchers.IO) {
//            _notes.emit(noteRepository.loadNotes())
            val notes = noteRepository.loadNotes()
            _notes.emit(notes)
        }
    }
    override fun getInitialState(): SearchState = SearchState(isLoading = false)

}
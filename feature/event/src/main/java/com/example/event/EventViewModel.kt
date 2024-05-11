package com.example.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor():BaseViewModel<EventState,EventEffect, EventEvent>() {
    private val _livedata= MutableLiveData<String>()
    val liveData :LiveData<String> = _livedata

    override fun getInitialState(): EventState = EventState(isLoading = false)

}
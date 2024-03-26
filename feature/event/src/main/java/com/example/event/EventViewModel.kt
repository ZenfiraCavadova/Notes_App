package com.example.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EventViewModel:ViewModel() {
    private val _livedata= MutableLiveData<String>()
    val liveData :LiveData<String> = _livedata
}
package com.example.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel:ViewModel() {
    private val _livedata=MutableLiveData<String>()
    val liveData :LiveData<String> = _livedata
}

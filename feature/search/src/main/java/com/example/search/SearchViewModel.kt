package com.example.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel:ViewModel() {
    private val _livedata= MutableLiveData<String>()
    val liveData:LiveData<String> = _livedata
}
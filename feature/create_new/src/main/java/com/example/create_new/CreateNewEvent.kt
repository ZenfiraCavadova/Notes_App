package com.example.create_new

sealed class CreateNewEvent {
    data class SaveNote(val title:String,val description:String):CreateNewEvent()
}
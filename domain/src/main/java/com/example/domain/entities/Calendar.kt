package com.example.domain.entities

data class Calendar(
    val eventId: Long,
    val name: String,
    val duration: String,
    val beginTime: Long?,
    val endTime: Long?,

)

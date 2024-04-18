package com.example.domain.entities.response_models

import com.google.gson.annotations.SerializedName

data class GetAllNotesResponseModel(
    @SerializedName("notes")
    val notes:List<NoteResponseModel>
)
data class NoteResponseModel(
    @SerializedName("title")
    val title: String,
    @SerializedName("sub_title")
    val description: String
)

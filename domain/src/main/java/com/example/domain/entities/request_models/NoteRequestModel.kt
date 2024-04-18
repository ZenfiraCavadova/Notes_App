package com.example.domain.entities.request_models
import com.google.gson.annotations.SerializedName
data class NoteRequestModel(
    @SerializedName("title")
    val title:String,

    @SerializedName("sub_title")
    val description:String
)


package com.example.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "notes-database")
data class Note (
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description:String,
    @ColumnInfo("creationDate")
    val creationDate:Long,
    @ColumnInfo("ID")
    @PrimaryKey(autoGenerate = true)
    val id:Long=0
)
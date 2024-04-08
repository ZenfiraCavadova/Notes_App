package com.example.data.database.db

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    lateinit var database: AppDatabase
    fun initDatabase(context: Context){
        database = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "notes_database"
        )
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
    }

}
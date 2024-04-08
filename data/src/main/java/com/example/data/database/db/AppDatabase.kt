package com.example.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.domain.entities.Note
import com.example.data.database.daos.NotesDao
@Database(entities = [Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    companion object {
        private const val DATABASE_NAME = "app_database.db"

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE notes_database ADD COLUMN creationDate INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}
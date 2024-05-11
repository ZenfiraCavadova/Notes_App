package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.daos.NotesDao
import com.example.data.database.db.AppDatabase
import com.example.data.database.db.Migrations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "notes-db"
        )
            .addMigrations(
                Migrations.MIGRATION_1_2
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(
        appDatabase: AppDatabase
    ): NotesDao {
        return appDatabase.notesDao()
    }
}
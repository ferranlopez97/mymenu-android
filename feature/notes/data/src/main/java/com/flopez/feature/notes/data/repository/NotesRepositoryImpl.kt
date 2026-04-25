package com.flopez.feature.notes.data.repository

import android.util.Log
import com.flopez.core.domain.dispatcher.DispatcherProvider
import com.flopez.feature.notes.data.datasource.NetworkDatasource
import com.flopez.feature.notes.domain.model.Note
import com.flopez.feature.notes.domain.repository.NotesRepository
import kotlinx.coroutines.withContext

class NotesRepositoryImpl(
    private val dispatchers: DispatcherProvider,
    private val networkDatasource: NetworkDatasource,
) : NotesRepository {

    override suspend fun getNotes(): List<Note> =
        withContext(dispatchers.IO) {
            val notes = networkDatasource.getNotes()
            Log.d("FERRAN", "getNotes: $notes")
            notes
        }

    override suspend fun createNote(title: String, subtitle: String?) {
        withContext(dispatchers.IO) { networkDatasource.createNote(title, subtitle) }
    }

    override suspend fun deleteNote(id: String) {
        withContext(dispatchers.IO) { networkDatasource.deleteNote(id) }
    }
}

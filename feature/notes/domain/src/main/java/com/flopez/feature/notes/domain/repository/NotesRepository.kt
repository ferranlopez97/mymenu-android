package com.flopez.feature.notes.domain.repository

import com.flopez.feature.notes.domain.model.Note

interface NotesRepository {
    suspend fun getNotes(): List<Note>
    suspend fun createNote(title: String, subtitle: String?)
    suspend fun deleteNote(id: String)
}

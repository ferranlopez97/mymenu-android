package com.flopez.feature.notes.data.datasource

import com.flopez.feature.notes.data.dto.NewNoteDto
import com.flopez.feature.notes.data.dto.NoteDto
import com.flopez.feature.notes.data.dto.toDomain
import com.flopez.feature.notes.domain.model.Note
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order

private const val NOTES_TABLE = "notes"

class NetworkDatasource(
    private val client: SupabaseClient,
) {

    suspend fun getNotes(): List<Note> =
        client.postgrest.from(NOTES_TABLE)
            .select {
                order(column = "created_at", order = Order.DESCENDING)
            }
            .decodeList<NoteDto>()
            .map { it.toDomain() }

    suspend fun createNote(title: String, subtitle: String?) {
        val userId = client.auth.currentUserOrNull()?.id
            ?: error("Not authenticated")
        client.postgrest.from(NOTES_TABLE).insert(
            NewNoteDto(userId = userId, title = title, subtitle = subtitle)
        )
    }

    suspend fun deleteNote(id: String) {
        client.postgrest.from(NOTES_TABLE).delete {
            filter { eq("id", id) }
        }
    }
}

package com.flopez.feature.notes.data.dto

import com.flopez.feature.notes.domain.model.Note
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NoteDto(
    val id: String,
    @SerialName("user_id") val userId: String,
    val title: String,
    val subtitle: String? = null,
    @SerialName("created_at") val createdAt: String,
)

@Serializable
data class NewNoteDto(
    @SerialName("user_id") val userId: String,
    val title: String,
    val subtitle: String? = null,
)

fun NoteDto.toDomain(): Note = Note(
    id = id,
    title = title,
    subtitle = subtitle,
    createdAt = createdAt,
)

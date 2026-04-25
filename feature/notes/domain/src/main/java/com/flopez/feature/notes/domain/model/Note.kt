package com.flopez.feature.notes.domain.model

data class Note(
    val id: String,
    val title: String,
    val subtitle: String?,
    val createdAt: String,
)

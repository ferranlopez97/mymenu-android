package com.flopez.feature.notes.di

import com.flopez.feature.notes.data.di.notesDataModule
import com.flopez.feature.notes.domain.di.notesDomainModule
import com.flopez.feature.notes.presentation.di.notesPresentationModule

val notesFeatureModule = listOf(
    notesDomainModule,
    notesDataModule,
    notesPresentationModule,
)

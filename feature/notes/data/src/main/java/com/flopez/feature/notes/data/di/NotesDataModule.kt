package com.flopez.feature.notes.data.di

import com.flopez.feature.notes.data.datasource.NetworkDatasource
import com.flopez.feature.notes.data.repository.NotesRepositoryImpl
import com.flopez.feature.notes.domain.repository.NotesRepository
import org.koin.dsl.module

val notesDataModule = module {
    single { NetworkDatasource(get()) }
    single<NotesRepository> { NotesRepositoryImpl(get(), get()) }
}

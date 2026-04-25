package com.flopez.feature.notes.domain.di

import com.flopez.feature.notes.domain.usecase.CreateNoteUseCase
import com.flopez.feature.notes.domain.usecase.DeleteNoteUseCase
import com.flopez.feature.notes.domain.usecase.GetNotesUseCase
import org.koin.dsl.module

val notesDomainModule = module {
    factory { GetNotesUseCase(get()) }
    factory { CreateNoteUseCase(get()) }
    factory { DeleteNoteUseCase(get()) }
}

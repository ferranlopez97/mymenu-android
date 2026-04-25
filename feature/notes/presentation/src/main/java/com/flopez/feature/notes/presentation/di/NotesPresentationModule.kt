package com.flopez.feature.notes.presentation.di

import com.flopez.feature.notes.presentation.notes.NotesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val notesPresentationModule = module {
    viewModel { NotesViewModel(get(), get(), get()) }
}

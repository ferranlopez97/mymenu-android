package com.flopez.feature.home.presentation.di

import com.flopez.feature.home.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homePresentationModule = module {
    viewModel { HomeViewModel(get()) }
}

package com.flopez.mymenu.di

import com.flopez.core.data.di.coreDataModule
import com.flopez.core.presentation.di.corePresentationModule
import com.flopez.feature.authentication.di.authFeatureModule
import com.flopez.feature.home.di.homeFeatureModule
import com.flopez.feature.notes.di.notesFeatureModule
import com.flopez.mymenu.root.RootViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val appPresentationModule = module {
    viewModel { RootViewModel(get()) }
}

val appModules = listOf(
    coreDataModule,
    corePresentationModule,
    appPresentationModule,
) + authFeatureModule + homeFeatureModule + notesFeatureModule

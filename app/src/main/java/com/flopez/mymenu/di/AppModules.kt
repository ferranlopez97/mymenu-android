package com.flopez.mymenu.di

import com.flopez.core.data.di.coreDataModule
import com.flopez.core.presentation.di.corePresentationModule
import com.flopez.feature.authentication.di.authFeatureModule

val appModules = listOf(
    coreDataModule,
    corePresentationModule,
) + authFeatureModule

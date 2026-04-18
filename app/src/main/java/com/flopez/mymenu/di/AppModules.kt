package com.flopez.mymenu.di

import com.flopez.core.data.di.coreDataModule
import com.flopez.feature.authentication.di.authFeatureModule

val appModules = listOf(
    coreDataModule,
) + authFeatureModule

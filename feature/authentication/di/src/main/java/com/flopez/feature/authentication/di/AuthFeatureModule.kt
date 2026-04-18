package com.flopez.feature.authentication.di

import com.flopez.feature.authentication.data.di.authDataModule
import com.flopez.feature.authentication.domain.di.authDomainModule
import com.flopez.feature.authentication.presentation.di.authPresentationModule

val authFeatureModule = listOf(
    authDataModule,
    authDomainModule,
    authPresentationModule,
)

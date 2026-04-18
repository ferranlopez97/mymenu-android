package com.flopez.feature.authentication.data.di

import com.flopez.feature.authentication.data.repository.AuthRepositoryImpl
import com.flopez.feature.authentication.domain.repository.AuthRepository
import org.koin.dsl.module

val authDataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}

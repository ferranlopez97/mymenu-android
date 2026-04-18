package com.flopez.feature.authentication.domain.di

import com.flopez.feature.authentication.domain.usecase.LoginUseCase
import org.koin.dsl.module

val authDomainModule = module {
    factory { LoginUseCase(get()) }
}

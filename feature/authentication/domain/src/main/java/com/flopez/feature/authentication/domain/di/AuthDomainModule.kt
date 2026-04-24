package com.flopez.feature.authentication.domain.di

import com.flopez.feature.authentication.domain.usecase.LoginUseCase
import com.flopez.feature.authentication.domain.usecase.LogoutUseCase
import com.flopez.feature.authentication.domain.usecase.ObserveCurrentUserEmailUseCase
import com.flopez.feature.authentication.domain.usecase.ObserveSessionUseCase
import com.flopez.feature.authentication.domain.usecase.RegisterUseCase
import org.koin.dsl.module

val authDomainModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { LogoutUseCase(get()) }
    factory { ObserveSessionUseCase(get()) }
    factory { ObserveCurrentUserEmailUseCase(get()) }
}

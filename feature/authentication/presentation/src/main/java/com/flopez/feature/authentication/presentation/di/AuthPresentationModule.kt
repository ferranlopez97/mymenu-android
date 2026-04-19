package com.flopez.feature.authentication.presentation.di

import com.flopez.feature.authentication.presentation.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authPresentationModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
}

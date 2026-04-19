package com.flopez.core.presentation.di

import com.flopez.core.presentation.string.AndroidStringProvider
import com.flopez.core.presentation.string.StringProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val corePresentationModule = module {
    single<StringProvider> { AndroidStringProvider(androidContext()) }
}

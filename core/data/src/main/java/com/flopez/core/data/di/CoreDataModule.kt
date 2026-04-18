package com.flopez.core.data.di

import com.flopez.core.domain.dispatcher.DispatcherProvider
import com.flopez.core.domain.dispatcher.StandardDispatchers
import org.koin.dsl.module

val coreDataModule = module {
    single<DispatcherProvider> { StandardDispatchers() }
    // Infraestructura compartida: OkHttpClient, Retrofit, AppDatabase, DataStore…
}

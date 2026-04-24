package com.flopez.core.data.di

import com.flopez.core.data.BuildConfig
import com.flopez.core.domain.dispatcher.DispatcherProvider
import com.flopez.core.domain.dispatcher.StandardDispatchers
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.dsl.module
val coreDataModule = module {
    single<DispatcherProvider> { StandardDispatchers() }
    single {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY,
        ) {
            install(Auth)
            install(Postgrest)
        }
    }
}
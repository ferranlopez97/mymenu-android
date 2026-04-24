package com.flopez.feature.authentication.data.di

import com.flopez.feature.authentication.data.provider.AuthProvider
import com.flopez.feature.authentication.data.provider.SupabaseAuthProvider
import com.flopez.feature.authentication.data.repository.AuthRepositoryImpl
import com.flopez.feature.authentication.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import org.koin.dsl.module

val authDataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<AuthProvider> { SupabaseAuthProvider(get()) }
    single<FirebaseAuth> { Firebase.auth }
}

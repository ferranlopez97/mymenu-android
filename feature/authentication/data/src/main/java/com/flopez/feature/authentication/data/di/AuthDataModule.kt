package com.flopez.feature.authentication.data.di

import com.flopez.feature.authentication.data.provider.AuthProvider
import com.flopez.feature.authentication.data.provider.FirebaseAuthProvider
import com.flopez.feature.authentication.data.repository.AuthRepositoryImpl
import com.flopez.feature.authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val authDataModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<AuthProvider> { FirebaseAuthProvider(get()) }
    single< FirebaseAuth> { Firebase.auth }
}

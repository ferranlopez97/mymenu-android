package com.flopez.feature.authentication.data.repository

import com.flopez.core.domain.dispatcher.DispatcherProvider
import com.flopez.feature.authentication.data.provider.AuthProvider
import com.flopez.feature.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val dispatchers: DispatcherProvider,
    private val authProvider: AuthProvider,
) : AuthRepository {

    override suspend fun login(user: String, password: String) : Boolean {
        return withContext(dispatchers.IO) {
            authProvider.login(user, password)
        }
    }

    override suspend fun register(
        user: String,
        password: String
    ) : Boolean {
        return withContext(dispatchers.IO) {
            authProvider.register(user, password)
        }
    }

    override suspend fun logout() {
        authProvider.logout()
    }
}
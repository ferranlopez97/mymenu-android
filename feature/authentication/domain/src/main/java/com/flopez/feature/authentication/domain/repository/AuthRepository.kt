package com.flopez.feature.authentication.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(user: String, password: String) : Boolean
    suspend fun register(user: String, password: String): Boolean

    suspend fun logout()

    fun observeSession(): Flow<Boolean>

    fun observeUserEmail(): Flow<String?>
}

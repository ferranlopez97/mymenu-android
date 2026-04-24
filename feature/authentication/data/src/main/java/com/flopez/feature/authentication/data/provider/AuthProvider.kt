package com.flopez.feature.authentication.data.provider

import kotlinx.coroutines.flow.Flow

interface AuthProvider {
    suspend fun login(user: String, pwd: String) : Boolean
    suspend fun register(user: String, pwd: String) : Boolean
    suspend fun logout()
    fun observeSession(): Flow<Boolean>
    fun observeUserEmail(): Flow<String?>
}

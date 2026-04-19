package com.flopez.feature.authentication.domain.repository

interface AuthRepository {

    suspend fun login(user: String, password: String) : Boolean
    suspend fun register(user: String, password: String): Boolean

    suspend fun logout()
}
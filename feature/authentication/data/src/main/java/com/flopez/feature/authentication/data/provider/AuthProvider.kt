package com.flopez.feature.authentication.data.provider

interface AuthProvider {
    suspend fun login(user: String, pwd: String) : Boolean
    suspend fun register(user: String, pwd: String) : Boolean
    suspend fun logout()
}
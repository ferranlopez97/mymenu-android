package com.flopez.feature.authentication.domain.repository

interface AuthRepository {

    suspend fun login(user: String, pwd: String) : Boolean
}
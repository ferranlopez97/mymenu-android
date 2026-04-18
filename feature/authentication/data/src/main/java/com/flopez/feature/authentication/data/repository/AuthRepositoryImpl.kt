package com.flopez.feature.authentication.data.repository

import com.flopez.core.domain.dispatcher.DispatcherProvider
import com.flopez.feature.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class AuthRepositoryImpl(
    private val dispatchers: DispatcherProvider
) : AuthRepository {

    override suspend fun login(user: String, pwd: String) : Boolean {
        return withContext(dispatchers.IO) {
            delay(1.5.seconds)
            Random.nextInt(0, 10)  > 5
        }
    }
}
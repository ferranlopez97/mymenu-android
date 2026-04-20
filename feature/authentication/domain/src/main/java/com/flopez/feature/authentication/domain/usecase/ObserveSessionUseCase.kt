package com.flopez.feature.authentication.domain.usecase

import com.flopez.feature.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class ObserveSessionUseCase(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<Boolean> = authRepository.observeSession()
}

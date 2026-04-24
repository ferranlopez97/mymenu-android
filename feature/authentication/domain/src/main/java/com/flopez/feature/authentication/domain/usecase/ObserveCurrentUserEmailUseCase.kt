package com.flopez.feature.authentication.domain.usecase

import com.flopez.feature.authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class ObserveCurrentUserEmailUseCase(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<String?> = authRepository.observeUserEmail()
}

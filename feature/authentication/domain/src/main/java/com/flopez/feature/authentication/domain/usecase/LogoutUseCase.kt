package com.flopez.feature.authentication.domain.usecase

import com.flopez.core.domain.usecase.base.NoParamsUseCase
import com.flopez.core.domain.usecase.base.UseCaseResult
import com.flopez.feature.authentication.domain.repository.AuthRepository

class LogoutUseCase(
    private val authRepository: AuthRepository,
) : NoParamsUseCase<Unit>() {

    override suspend fun run(): UseCaseResult<Unit> {
        authRepository.logout()
        return UseCaseResult.Success(Unit)
    }
}

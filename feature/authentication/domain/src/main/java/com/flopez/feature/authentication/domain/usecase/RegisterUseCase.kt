package com.flopez.feature.authentication.domain.usecase

import com.flopez.core.domain.usecase.base.UseCase
import com.flopez.core.domain.usecase.base.UseCaseResult
import com.flopez.feature.authentication.domain.repository.AuthRepository

class RegisterUseCase(
    private val authRepository: AuthRepository,
) : UseCase<RegisterUseCase.Params, Unit>() {

    override suspend fun run(params: Params): UseCaseResult<Unit> {
        val result = authRepository.register(
            user = params.username,
            password = params.password,
        )
        return if (result) UseCaseResult.Success(Unit)
        else UseCaseResult.Error(Exception("Registration failed"))
    }

    data class Params(
        val username: String,
        val password: String,
    )
}

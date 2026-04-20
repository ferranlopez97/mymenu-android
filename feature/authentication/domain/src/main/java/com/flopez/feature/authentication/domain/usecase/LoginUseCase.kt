package com.flopez.feature.authentication.domain.usecase

import com.flopez.core.domain.usecase.base.UseCase
import com.flopez.core.domain.usecase.base.UseCaseResult
import com.flopez.feature.authentication.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
) : UseCase<LoginUseCase.Params, Unit>() {

    override suspend fun run(params: Params): UseCaseResult<Unit> {
        val result = authRepository.login(
            user = params.username,
            password = params.password
        )

        return if (result) UseCaseResult.Success(Unit) else UseCaseResult.Error(Exception("errororor xd"))
    }

    data class Params(
        val username: String,
        val password: String,
    )
}

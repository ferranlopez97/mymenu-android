package com.flopez.core.domain.usecase.base

abstract class UseCase<in Params, out T> {

    suspend fun execute(params: Params): UseCaseResult<T> = try {
        run(params)
    } catch (e: Exception) {
        UseCaseResult.Error(e)
    }

    protected abstract suspend fun run(params: Params): UseCaseResult<T>
}

abstract class NoParamsUseCase<out T> {

    suspend fun execute(): UseCaseResult<T> = try {
        run()
    } catch (e: Exception) {
        UseCaseResult.Error(e)
    }

    protected abstract suspend fun run(): UseCaseResult<T>
}

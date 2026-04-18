package com.flopez.core.domain.usecase.base

abstract class UseCase<in Params, out T> {
    abstract suspend fun execute(params: Params): UseCaseResult<T>
}

abstract class NoParamsUseCase<out T> {
    abstract suspend fun execute(): UseCaseResult<T>
}

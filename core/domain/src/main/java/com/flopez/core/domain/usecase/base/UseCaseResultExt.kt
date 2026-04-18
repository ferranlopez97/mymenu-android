package com.flopez.core.domain.usecase.base

fun <T> UseCaseResult<T>.onSuccess(action: (T) -> Unit): UseCaseResult<T> {
    if (this is UseCaseResult.Success) action(data)
    return this
}

fun <T> UseCaseResult<T>.onError(action: (Exception) -> Unit): UseCaseResult<T> {
    if (this is UseCaseResult.Error) action(exception)
    return this
}

fun <T> UseCaseResult<T>.finally(action: () -> Unit): UseCaseResult<T> {
    action()
    return this
}

package com.flopez.core.domain.usecase.base

sealed class UseCaseResult<out T> {
    data class Success<T>(val data: T) : UseCaseResult<T>()
    data class Error(val exception: Exception) : UseCaseResult<Nothing>()
}
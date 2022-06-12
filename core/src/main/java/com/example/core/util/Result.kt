package com.example.core.util

sealed class Result<out S, out E : Exception> {

    data class Success<S>(val value: S) : Result<S, Nothing>()

    data class Failure<out E : Exception>(val error: E) : Result<Nothing, E>()

}

inline fun <T, E : Exception, R> Result<T, E>.map(transform: (value: T) -> R): Result<R, E> {
    return when(this) {
        is Result.Success -> Result.Success(transform(value))
        is Result.Failure<E> -> this
    }
}

inline fun <T, E : Exception> Result<T, E>.onFailure(block: (Result.Failure<E>) -> Nothing): T {
    return when(this) {
        is Result.Success -> this.value
        is Result.Failure<E> -> block(this)
    }
}

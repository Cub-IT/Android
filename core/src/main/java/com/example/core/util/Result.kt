package com.example.core.util

sealed class Result<out S, out E : Exception> {

    sealed class Success<S> : Result<S, Nothing>() {
        class Value<S>(val value: S) : Success<S>()
        object Empty : Success<Nothing>()
    }

    data class Failure<out E : Exception>(
        val error: E,
        val cause: Throwable
    ) : Result<Nothing, E>()

}

/*sealed class Result<out T> {

    sealed class Success<T> : Result<T>() {
        class Value<T>(val value: T) : Success<T>()
        object Empty : Success<Nothing>()
    }

    data class Failure<out T : Exception>(
        val error: T,
        val reason: Exception
    ) : Result<T>()

}*/

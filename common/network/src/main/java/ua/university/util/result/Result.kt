package ua.university.util.result

sealed class Result<out S, out E> {
    data class Success<out S>(val success: S) : Result<S, Nothing>()
    data class Failure<out E>(val cause: E) : Result<Nothing, E>()
}

inline fun <T, S, E> Result<S, E>.flatMap(transform: (S) -> Result<T, E>): Result<T, E> {
    return when (this) {
        is Result.Success -> transform(success)
        is Result.Failure -> this
    }
}

fun <T, S, E> Result<S, E>.map(transform: (S) -> (T)): Result<T, E> {
    return when (this) {
        is Result.Success -> Result.Success(transform(success))
        is Result.Failure -> this
    }
}

inline fun <S, E> Result<S, E>.onFailure(block: (Result.Failure<E>) -> Nothing): S {
    return when(this) {
        is Result.Success -> this.success
        is Result.Failure -> block(this)
    }
}

inline fun <S, E> Result<S, E>.onResult(
    onSuccess: (Result.Success<S>) -> Unit,
    onFailure: (Result.Failure<E>) -> Unit
) {
    when(this) {
        is Result.Success -> onSuccess(this)
        is Result.Failure -> onFailure(this)
    }
}

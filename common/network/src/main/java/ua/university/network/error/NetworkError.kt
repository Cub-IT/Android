package ua.university.network.error

import retrofit2.HttpException

sealed class NetworkError(open val message: String?) {

    class UnknownError(message: String?, cause: Throwable? = null) : NetworkError(message)

    sealed class ServerError(message: String?) : NetworkError(message) {

    }

    sealed class ClientError(message: String?, val cause: Exception) : NetworkError(message) {

        class NetworkConnectionError(message: String?, cause: Exception) : ClientError(message, cause)

        class ResponseParseError(message: String?, cause: Exception) : ClientError(message, cause)

        class HttpError(message: String?, cause: HttpException) : ClientError(message, cause)
    }
}

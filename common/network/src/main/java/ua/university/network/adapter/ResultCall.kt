package ua.university.network.adapter

import ua.university.network.result.Result
import okio.IOException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import ua.university.network.error.NetworkError
import ua.university.network.result.NetworkResult

internal class ResultCall<T>(proxy: Call<T>) : CallDelegate<T, NetworkResult<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<NetworkResult<T>>) {
        proxy.enqueue(ResultCallback(this, callback))
    }

    override fun cloneImpl(): ResultCall<T> {
        return ResultCall(proxy.clone())
    }

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<NetworkResult<T>>
    ) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val result: NetworkResult<T> = if (response.isSuccessful) {
                Result.Success(
                    success = response.body() as T
                )
            } else {
                Result.Failure(
                    cause = NetworkError.ClientError.HttpError(
                        message = null,
                        cause = HttpException(response)
                    )
                )
            }
            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = when (error) {
                is HttpException -> NetworkError.ClientError.HttpError(message = error.localizedMessage, cause = error)
                is IOException -> NetworkError.ClientError.NetworkConnectionError(message = "The server is not responding", cause = error)

                else -> NetworkError.UnknownError(message = "No internet connection", cause = error)
            }
            callback.onResponse(proxy, Response.success(Result.Failure(result)))
        }
    }

    override fun timeout(): Timeout {
        return proxy.timeout()
    }

}
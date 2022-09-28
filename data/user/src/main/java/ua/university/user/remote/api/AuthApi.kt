package ua.university.user.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import ua.university.user.remote.entry.LogInRequestEntry
import ua.university.user.remote.entry.SignResponseEntry
import ua.university.user.remote.entry.SignUpRequestEntry
import ua.university.util.result.NetworkResult

interface AuthApi {
    @POST("login")
    suspend fun logIn(
        @Body logInRequestEntry: LogInRequestEntry
    ): NetworkResult<SignResponseEntry>

    @POST("new")
    suspend fun signUp(
        @Body signUpRequestEntry: SignUpRequestEntry
    ): NetworkResult<SignResponseEntry>
}
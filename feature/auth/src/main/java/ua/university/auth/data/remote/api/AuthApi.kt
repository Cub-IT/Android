package ua.university.auth.data.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import ua.university.auth.data.remote.entry.SignInRequestEntry
import ua.university.auth.data.remote.entry.SignResponseEntry
import ua.university.auth.data.remote.entry.SignUpRequestEntry
import ua.university.network.result.NetworkResult

interface AuthApi {

    @POST("login")
    suspend fun signIn(
        @Body signInRequestEntry: SignInRequestEntry
    ): NetworkResult<SignResponseEntry>

    @POST("registration")
    suspend fun signUp(
        @Body signUpRequestEntry: SignUpRequestEntry
    ): NetworkResult<Unit>

}